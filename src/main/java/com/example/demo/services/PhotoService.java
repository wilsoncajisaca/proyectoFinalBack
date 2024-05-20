package com.example.demo.services;

import com.example.demo.entities.Photo;
import com.example.demo.repositories.IPhotoRepository;
import com.example.demo.services.interfaces.IPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class PhotoService implements IPhotoService {
    @Autowired
    private IPhotoRepository photoRepository;
    @Override
    public void createPhoto(String url, UUID sinisterId) {
        log.info("Service for create the photo");
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setSinisterId(sinisterId);
        photoRepository.save(photo);
    }

    @Override
    public Set<Photo> getPhotoByUserId(UUID sinisterId) {
        log.info("Service for get the photo");
        return photoRepository.findBySinisterId(sinisterId);
    }

}
