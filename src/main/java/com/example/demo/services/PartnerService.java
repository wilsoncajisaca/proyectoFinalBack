package com.example.demo.services;

import com.example.demo.entities.Partner;
import com.example.demo.exception.GeneralException;
import com.example.demo.mappers.PartnerMapper;
import com.example.demo.pojos.errors.ApiError;
import com.example.demo.pojos.inputs.PartnerINP;
import com.example.demo.pojos.outputs.PartnerWithPaidDTO;
import com.example.demo.repositories.IPartnerRepository;
import com.example.demo.services.interfaces.IPartnerService;
import com.example.demo.tools.PartnerTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PartnerService extends PartnerTool implements IPartnerService {
    @Autowired
    private IPartnerRepository partnerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Partner getByIdentification(String identification) throws RuntimeException {
        log.debug("Service show a partner by identification");
        return partnerRepository.findByIdentificationAndStatus(identification, true)
                .orElseThrow(() -> new GeneralException(new ApiError("El valor no existe")));
    }

    @Override
    public List<PartnerWithPaidDTO> getAllPartner() throws RuntimeException {
        log.debug("Service to list a partner");
        return partnerRepository.findPartnersWithPaid()
                .stream()
                .map(this::castPartnerWithPaid)
                .collect(Collectors.toList());
    }

    @Override
    public void createPartner(PartnerINP partnerINP) throws RuntimeException {
        log.debug("Service to save a partner : {}", partnerINP);
        existPartnerByIdentification(partnerINP.getIdentification());
        Partner partnerEntity = PartnerMapper.toEntityPartner(partnerINP);
        Partner partnerSaved = partnerRepository.save(partnerEntity);
        this.createAuthForEmployee(partnerSaved, passwordEncoder.encode(partnerINP.getPassword()));
    }

    @Override
    public Partner updatePartner(PartnerINP partnerINP) throws RuntimeException {
        return null;
    }

    private void existPartnerByIdentification(String identification) {
        partnerRepository.findByIdentificationAndStatus(identification, true)
                .stream().findAny().ifPresent(s -> {
                    throw new GeneralException(new ApiError("Ya existe un socio con el numero de cedula ingresado."));
                });
    }



    private void createAuthForEmployee(Partner partner, String password){
        partner.setAuths(createAuth(partner, password));
        partnerRepository.save(partner);
    }

    private PartnerWithPaidDTO castPartnerWithPaid(Object[] obj){
        return PartnerWithPaidDTO.builder()
                .partnerId(obj[0].toString())
                .identification((String) obj[1])
                .names((String) obj[2])
                .lastname((String) obj[3])
                .phone(obj[4] == null ? "" : obj[4].toString())
                .gender((Integer) obj[5])
                .paid((Integer) obj[6])
                .build();
    }
}
