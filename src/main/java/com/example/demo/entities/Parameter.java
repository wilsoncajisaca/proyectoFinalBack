package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.demo.entities.auditoria.Audit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Entity
@Table(name = "parametros")
public class Parameter extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_parametro",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "parametro_id", updatable = false, nullable = false)
    private UUID parameterId;

    @Column(name = "nombre", length = 100, nullable = false)
    @With
    private String name;

    @Column(name = "valor", length = 100, nullable = false)
    @With
    private String value;

    @Tolerate
    public Parameter (){
        super();
    }
}
