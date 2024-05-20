package com.example.demo.pojos.inputs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Tolerate;

import java.util.UUID;
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class AuthINP {
    @With
    private UUID id;

    @With
    private UUID idEmployee;

    @NotEmpty(message = "No se encontro el usuario")
    @Size(min = 3, max = 150)
    @With
    private String username;

    @NotEmpty(message = "No se encontro ninguna contraseña")
    @Size(min = 3, max = 25)
    @With
    private String password;

    @Tolerate
    public AuthINP(){super();}
}
