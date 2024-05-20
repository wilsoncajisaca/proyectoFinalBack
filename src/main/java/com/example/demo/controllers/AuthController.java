package com.example.demo.controllers;

import com.example.demo.commons.Commons;
import com.example.demo.pojos.inputs.AuthINP;
import com.example.demo.services.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("login")
    public @ResponseBody ResponseEntity<Object> login(@Valid @RequestBody AuthINP authINP,
                                                      Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        log.debug("Inicio proceso de login: {}", authINP);
        return ResponseEntity.ok().body(authService.autenticate(authINP));
    }
}
