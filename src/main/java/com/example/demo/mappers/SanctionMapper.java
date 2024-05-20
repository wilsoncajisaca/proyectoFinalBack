package com.example.demo.mappers;

import com.example.demo.entities.Sanction;
import com.example.demo.pojos.inputs.SanctionINP;

public class SanctionMapper {
    public static Sanction toEntity(SanctionINP sanctionINP){
        return Sanction.builder()
                .partnerId(sanctionINP.getPartnerId())
                .sanctionDate(sanctionINP.getSanctionDate())
                .paid(0)
                .build();
    }
}
