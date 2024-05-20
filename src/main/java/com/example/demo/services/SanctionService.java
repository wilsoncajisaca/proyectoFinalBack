package com.example.demo.services;

import com.example.demo.entities.Parameter;
import com.example.demo.entities.Sanction;
import com.example.demo.exception.GeneralException;
import com.example.demo.mappers.SanctionMapper;
import com.example.demo.pojos.inputs.MeetingINP;
import com.example.demo.pojos.inputs.SanctionINP;
import com.example.demo.repositories.IParameterRepository;
import com.example.demo.repositories.ISanctionRepository;
import com.example.demo.services.interfaces.ISanctionService;
import com.example.demo.tools.ExceptionTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SanctionService extends ExceptionTool implements ISanctionService {
    public static final String VALOR_MULTA = "VALOR_MULTA";
    @Autowired
    private ISanctionRepository sanctionRepository;
    @Autowired
    private IParameterRepository parameterRepository;
    @Override
    public List<Sanction> getAllSanctionByPartner(UUID partnerId) throws GeneralException {
        log.info("Service to list the sanctions");
        return sanctionRepository.findByPartnerIdAndPaidAndStatus(partnerId, 0, true);
    }

    @Override
    public Map<String, String> meetingList(List<MeetingINP> meetingINPS) throws GeneralException {
        Map<String,String> response = new HashMap<>();
        List<MeetingINP> filteredMeetings = meetingINPS.stream()
                .filter(meeting -> Boolean.FALSE.equals(meeting.getPresence()))
                .collect(Collectors.toList());
        if(!filteredMeetings.isEmpty()){
            this.createSanction(filteredMeetings.stream()
                    .map(this::castToSanction)
                    .collect(Collectors.toList()));
        }
        response.put("response", "Listado Completado.");
        return response;
    }

    @Override
    public Map<String, String> paySantion(UUID sanctionId) throws GeneralException {
        Map<String,String> response = new HashMap<>();
        Sanction sanction = sanctionRepository.findById(sanctionId).orElseThrow(this::sanctionNotFound);
        sanction.setPaid(1);
        sanctionRepository.save(sanction);
        response.put("response", "Multa cobrada exitosamente");
        return response;
    }

    @Override
    public Map<String, String> payAllSantion(List<SanctionINP> sanctionINPs) throws GeneralException {
        Map<String,String> response = new HashMap<>();
        sanctionINPs.forEach(san -> paySantion(san.getSanctionId()));
        response.put("response", "Multas cobradas exitosamente");
        return response;
    }

    @Override
    public void createSanction(List<SanctionINP> sanctionINP) throws GeneralException {
        log.info("Service for create the sanctions");
        for (SanctionINP s : sanctionINP) {
            Sanction sanctionEnt = SanctionMapper.toEntity(s);
            Parameter valorMulta = getParameterForSanction(VALOR_MULTA);
            if (valorMulta != null && valorMulta.getValue() != null) {
                sanctionEnt.setValue(Double.parseDouble(valorMulta.getValue()));
            }
            this.sanctionRepository.save(sanctionEnt);
        }
    }

    private SanctionINP castToSanction(MeetingINP meetingINP){
        SanctionINP sanctionINP = new SanctionINP();
        sanctionINP.setPartnerId(meetingINP.getPartnerId());
        sanctionINP.setSanctionDate(meetingINP.getMeetingDate());
        sanctionINP.setPaid(0);
        return sanctionINP;
    }

    private Parameter getParameterForSanction(String name) {
        return parameterRepository.findByNameAndStatus(name, true)
                .orElseThrow(this::parameterNotFound);
    }
}
