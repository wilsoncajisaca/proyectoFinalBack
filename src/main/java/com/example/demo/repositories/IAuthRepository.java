package com.example.demo.repositories;

import com.example.demo.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Serializable> {
    Optional<Auth> findByUsernameAndStatus(String userName, Boolean status);
    Optional<Auth> findByUsername(String username);
}
