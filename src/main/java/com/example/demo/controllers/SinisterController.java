package com.example.demo.controllers;

import com.example.demo.commons.Commons;
import com.example.demo.pojos.inputs.PartnerINP;
import com.example.demo.pojos.inputs.SinisterINP;
import com.example.demo.pojos.outputs.SinisterOUT;
import com.example.demo.services.interfaces.ISinisterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/sinister/")
public class SinisterController {
    @Autowired
    private ISinisterService sinisterService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getAllSinister", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllSinister() throws Exception {
        return ResponseEntity.ok().body(sinisterService.getAllSinister());
    }
    @GetMapping(value = "getAllSinisterByPartner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllSinisterByPartner() throws Exception {
        return ResponseEntity.ok().body(sinisterService.getAllSinisterByPartner());
    }

    @GetMapping(value = "getSinister", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSinister(@RequestParam UUID sinisterId) throws Exception {
        SinisterOUT sinister = sinisterService.getSinister(sinisterId);
        return ResponseEntity.ok().body(sinister);
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void create(@Valid @RequestBody SinisterINP siniesterINP, Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        sinisterService.createSinister(siniesterINP);
    }

}
