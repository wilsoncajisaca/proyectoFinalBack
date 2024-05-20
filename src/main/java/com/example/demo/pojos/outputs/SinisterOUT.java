package com.example.demo.pojos.outputs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SinisterOUT {
    private UUID siniestroId;
    private Set<String> urlFotos;
    private String ubicacion;
    private String observacion;
    private String tipoSiniestro;
    private String fechaSiniestro;
    @Tolerate
    public SinisterOUT() {
        super();
    }
}