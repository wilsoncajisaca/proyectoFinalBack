package com.example.demo.services.interfaces;

import com.example.demo.entities.Photo;

import java.util.Set;
import java.util.UUID;

public interface IPhotoService {
    void createPhoto(String url, UUID sinisterId);
    Set<Photo> getPhotoByUserId(UUID partnerId);
}
