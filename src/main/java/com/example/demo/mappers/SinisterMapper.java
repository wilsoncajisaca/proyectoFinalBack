package com.example.demo.mappers;

import com.example.demo.entities.Sinister;
import com.example.demo.pojos.inputs.SinisterINP;

public class SinisterMapper {
    public static Sinister toEntity(SinisterINP sinisterINP) {
        return Sinister.builder()
                .partnerId(sinisterINP.getPartnerId())
                .location(sinisterINP.getSinisterLocation())
                .sinisterTypeId(sinisterINP.getSinisterType())
                .description(sinisterINP.getObservation())
                .build();
    }
}
