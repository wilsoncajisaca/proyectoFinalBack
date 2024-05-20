package com.example.demo.repositories;

import com.example.demo.entities.Sinister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ISinisterRepository extends JpaRepository<Sinister, Serializable> {
    Set<Sinister> findAllByPartnerId(UUID partnerId);
    Set<Sinister> findAllByStatus(Boolean status);
}
