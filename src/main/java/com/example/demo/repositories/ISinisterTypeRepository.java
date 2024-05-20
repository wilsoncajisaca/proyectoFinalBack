package com.example.demo.repositories;

import com.example.demo.entities.SinisterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ISinisterTypeRepository extends JpaRepository<SinisterType, Serializable> {
}