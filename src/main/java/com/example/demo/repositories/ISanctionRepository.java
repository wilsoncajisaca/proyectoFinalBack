package com.example.demo.repositories;

import com.example.demo.entities.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISanctionRepository extends JpaRepository<Sanction, Serializable> {
    List<Sanction> findByPartnerIdAndPaidAndStatus(UUID partnerId, Integer paid, Boolean status);
}