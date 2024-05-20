package com.example.demo.mappers;

import com.example.demo.entities.Partner;
import com.example.demo.pojos.inputs.PartnerINP;

public class PartnerMapper {
    public static Partner toEntityPartner(PartnerINP partnerINP){
        return Partner.builder()
                .identification(partnerINP.getIdentification())
                .names(partnerINP.getNames())
                .lastname(partnerINP.getLastname())
                .address(partnerINP.getAddress())
                .email(partnerINP.getEmail())
                .phone(partnerINP.getPhone())
                .gender(partnerINP.getGender())
                .build();
    }
}
