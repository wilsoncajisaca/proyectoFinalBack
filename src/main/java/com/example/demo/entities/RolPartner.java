package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.demo.entities.auditoria.Audit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Entity
@Table(name = "rol_socio")
public class RolPartner extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_rol_socio",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "rol_socio_id", updatable = false, nullable = false)
    private UUID id;

    @Size(min = 3, max = 25)
    @Column(name = "nombre", length = 25)
    @With
    private String name;

    @Size(min = 3, max = 150)
    @Column(name = "descripcion", length = 150)
    @With
    private String description;

    @Tolerate
    public RolPartner() {
        super();
    }
}
