package com.example.demo.services;

import com.example.demo.entities.Photo;
import com.example.demo.entities.Sinister;
import com.example.demo.mappers.SinisterMapper;
import com.example.demo.mappers.SinisterOutMapper;
import com.example.demo.pojos.inputs.SinisterINP;
import com.example.demo.pojos.outputs.SinisterOUT;
import com.example.demo.repositories.ISinisterRepository;
import com.example.demo.security.UserAuthentication;
import com.example.demo.services.interfaces.IPartnerService;
import com.example.demo.services.interfaces.IPhotoService;
import com.example.demo.services.interfaces.ISinisterService;
import com.example.demo.tools.ExceptionTool;
import io.jsonwebtoken.lang.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SinisterService extends ExceptionTool implements ISinisterService {
    @Autowired
    private ISinisterRepository sinisterRepository;
    @Autowired
    private FirebaseFileService firebaseFileService;
    @Autowired
    private IPhotoService photoService;
    @Autowired
    private IPartnerService partnerService;
    @Override
    public void createSinister(SinisterINP sinister) throws IOException {
        log.info("Service for create the sinister");
        String fileUrl = Strings.EMPTY;
        if (StringUtils.isNotEmpty(sinister.getFileBase64())) {
            // Decodificar el archivo base64 en un arreglo de bytes
            byte[] fileBytes = Base64.getDecoder().decode(sinister.getFileBase64());
            // Crear un MultipartFile a partir del array de bytes
            MultipartFile multipartFile = new MockMultipartFile("file", "filename", "image/"+sinister.getExtensionFile(), fileBytes);
            fileUrl = firebaseFileService.saveFile(multipartFile, sinister.getExtensionFile());
            log.info("File url: " + fileUrl);
        }
        sinister.setPartnerId(getPartnerLoginId());
        Sinister sinisterEntity = SinisterMapper.toEntity(sinister);
        Sinister sinisterSaved = sinisterRepository.save(sinisterEntity);
        // Guardar la foto con la URL y el ID del siniestro
        if (StringUtils.isNotEmpty(fileUrl)) {
            photoService.createPhoto(fileUrl, sinisterSaved.getSinisterId());
        }
    }

    @Override
    public SinisterOUT getSinister(UUID sinisterId) {
        log.info("Service for get the sinister");
        Sinister sinister = sinisterRepository.findById(sinisterId).orElseThrow(() -> new RuntimeException("Sinister not found"));
        Set<String> photos = photoService.getPhotoByUserId(sinister.getSinisterId()).stream().map(Photo::getUrl).collect(Collectors.toSet());
        return SinisterOutMapper.toOutput(sinister, photos);
    }

    @Override
    public Set<SinisterOUT> getAllSinisterByPartner() {
        log.info("Service for get all the sinisters by partner");
        Set<SinisterOUT> sinisterOUTS = sinisterRepository.findAllByPartnerId(getPartnerLoginId())
                .stream()
                .map(sinister -> SinisterOutMapper.toOutput(sinister, null))
                .collect(Collectors.toSet());
        return sinisterOUTS;
    }

    @Override
    public Set<SinisterOUT> getAllSinister() {
        log.info("Service for get all the sinisters");
        Set<SinisterOUT> sinisterOUTS = sinisterRepository.findAllByStatus(true)
                .stream()
                .map(sinister -> SinisterOutMapper.toOutput(sinister, null))
                .collect(Collectors.toSet());
        return sinisterOUTS;
    }

    private UUID getPartnerLoginId(){
        return partnerService.getByIdentification(UserAuthentication.getUserNameV2()).getPartnerId();
    }
}
