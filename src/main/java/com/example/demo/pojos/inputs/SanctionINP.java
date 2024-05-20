package com.example.demo.pojos.inputs;

import com.example.demo.commons.Validations;
import com.example.demo.entities.Sanction;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.Tolerate;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SanctionINP {
    @JsonProperty("multa")
    @With
    private UUID sanctionId;

    @JsonProperty("socio")
    @With
    private UUID partnerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("fechaMulta")
    @With
    private Date sanctionDate;

    @JsonProperty("pagado")
    @With
    private Integer paid;

    @Tolerate
    public SanctionINP (){
        super();
    }
}
