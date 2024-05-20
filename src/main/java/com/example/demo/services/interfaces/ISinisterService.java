package com.example.demo.services.interfaces;

import com.example.demo.entities.Sinister;
import com.example.demo.pojos.inputs.SinisterINP;
import com.example.demo.pojos.outputs.SinisterOUT;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ISinisterService {
    void createSinister(SinisterINP sinister) throws IOException;
    SinisterOUT getSinister(UUID sinisterId);
    Set<SinisterOUT> getAllSinisterByPartner();
    Set<SinisterOUT> getAllSinister();
}
