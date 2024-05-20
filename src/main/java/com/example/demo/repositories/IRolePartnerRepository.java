package com.example.demo.repositories;

import com.example.demo.entities.RolPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRolePartnerRepository extends JpaRepository<RolPartner, Serializable> {
    List<RolPartner> findByStatus(Boolean status);

    Optional<RolPartner> findByName(String name);
}
