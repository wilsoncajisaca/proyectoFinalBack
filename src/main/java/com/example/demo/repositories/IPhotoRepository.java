package com.example.demo.repositories;

import com.example.demo.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, Serializable> {

    Set<Photo> findBySinisterId(UUID sinisterId);
}
