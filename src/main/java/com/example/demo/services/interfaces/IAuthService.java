package com.example.demo.services.interfaces;

import com.example.demo.pojos.inputs.AuthINP;

import java.util.Map;

public interface IAuthService {
    /**
     * Save a token
     *
     * @param auth the entity data to save
     * @return the persisted entity
     */
    Map<String, Object> autenticate(AuthINP auth) throws Exception;
}
