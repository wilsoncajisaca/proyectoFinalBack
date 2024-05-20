package com.example.demo.controllers;

import com.example.demo.commons.Commons;
import com.example.demo.entities.Sanction;
import com.example.demo.pojos.inputs.MeetingINP;
import com.example.demo.pojos.inputs.SanctionINP;
import com.example.demo.services.interfaces.ISanctionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/sanction/")
public class SanctionController {
    @Autowired
    private ISanctionService sanctionService;

    @GetMapping(value = "get-by-partner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSanctionByPartner(UUID partnerId) throws Exception {
        List<Sanction> sanctions = sanctionService.getAllSanctionByPartner(partnerId);
        return ResponseEntity.ok().body(sanctions);
    }

    @PostMapping(value = "meeting-list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMeeting(@Valid @RequestBody List<MeetingINP> meetingINPS, Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        return ResponseEntity.ok().body(sanctionService.meetingList(meetingINPS));
    }

    @PostMapping(value = "pay-sanction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> paySantion(@Valid @RequestBody SanctionINP sanctionINP, Errors errors) throws Exception{
        Commons.validateFieldRequest(errors);
        return ResponseEntity.ok().body(sanctionService.paySantion(sanctionINP.getSanctionId()));
    }

    @PostMapping(value = "pay-all-sanction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> payAllSantion(@Valid @RequestBody List<SanctionINP> sanctionINPs, Errors errors) throws Exception{
        Commons.validateFieldRequest(errors);
        return ResponseEntity.ok().body(sanctionService.payAllSantion(sanctionINPs));
    }
}
