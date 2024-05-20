package com.example.demo.repositories;

import com.example.demo.entities.Parameter;
import com.example.demo.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface IParameterRepository extends JpaRepository<Parameter, Serializable> {
    Optional<Parameter> findByNameAndStatus(String name, Boolean status);
}
