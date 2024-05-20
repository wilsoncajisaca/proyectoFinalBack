package com.example.demo.pojos.inputs;

import com.example.demo.commons.Validations;
import com.example.demo.validation.EcuatorianDNI;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PartnerINP implements Serializable {
    @With
    private UUID partnerId;

    @Size(min = 10,max = 10, message = "La cedula debe contener 10 caracteres")
    @NotNull(message = "La cedula no puede estar vacio")
    @EcuatorianDNI
    @JsonProperty("cedula")
    @With
    private String identification;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @JsonProperty("nombre")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Nombre no Valido")
    @With
    private String names;

    @NotEmpty(message = "El apellido no puede ser vacio")
    @JsonProperty("apellido")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Apellido no Valido")
    @With
    private String lastname;

    @NotEmpty(message = "La direccion no puede ser vacia")
    @JsonProperty("direccion")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Direccion no Valido")
    @With
    private String address;

    @NotEmpty(message = "La contraseña no puede ser vacia")
    @JsonProperty("contrasenia")
    @With
    private String password;

    @JsonProperty("email")
    @NotEmpty(message = "El email no puede ser vacio")
    @Pattern(regexp = Validations.REGEX_MAIL_VALIDATION, message = "Email no Valido")
    @With
    private String email;

    @JsonProperty("celular")
    @With
    @Size(min = 10, max = 10, message = "El numero celular debe contener 10 caracteres")
    private String phone;

    @JsonProperty("genero")
    @With
    private Integer gender;

    @JsonProperty("estado")
    @With
    private Boolean status;

    @Tolerate
    PartnerINP() {
        super();
    }
}
