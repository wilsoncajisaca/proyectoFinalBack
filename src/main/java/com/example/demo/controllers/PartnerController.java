package com.example.demo.controllers;

import com.example.demo.commons.Commons;
import com.example.demo.entities.Partner;
import com.example.demo.pojos.inputs.PartnerINP;
import com.example.demo.pojos.outputs.PartnerWithPaidDTO;
import com.example.demo.services.interfaces.IPartnerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/partner/")
public class PartnerController {
    @Autowired
    private IPartnerService partnerService;

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> test() {
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("response", "OK");
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@Valid @RequestBody PartnerINP partnerINP, Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        partnerService.createPartner(partnerINP);
    }

    @GetMapping(value = "get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllPartners() throws Exception {
        List<PartnerWithPaidDTO> partners = partnerService.getAllPartner();
        return ResponseEntity.ok().body(partners);
    }

    @GetMapping(value = "get-by-identification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByIdentification(@RequestParam String identification) throws Exception {
        Partner partner = partnerService.getByIdentification(identification);
        return ResponseEntity.ok().body(partner);
    }
}
