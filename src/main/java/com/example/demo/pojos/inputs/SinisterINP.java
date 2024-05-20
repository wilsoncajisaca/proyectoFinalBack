package com.example.demo.pojos.inputs;

import com.example.demo.security.UserAuthentication;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Tolerate;

import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SinisterINP {
    private UUID partnerId;
    private String fileBase64;
    @JsonProperty("extensionArchivo")
    @With
    private String extensionFile;
    @JsonProperty("ubicacionSiniestro")
    @With
    private String sinisterLocation;
    @JsonProperty("tipoSiniestro")
    @With
    private UUID sinisterType;
    @JsonProperty("descripcion")
    @With
    private String observation;

    @Tolerate
    SinisterINP() {
        super();
    }
}
