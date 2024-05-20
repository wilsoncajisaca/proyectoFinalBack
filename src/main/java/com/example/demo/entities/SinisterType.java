package com.example.demo.entities;

import com.example.demo.entities.auditoria.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "sinister_type")
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class SinisterType extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_sinister_type",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "sinister_type_id", updatable = false, nullable = false)
    private UUID sinisterTypeId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @Tolerate
    public SinisterType() {
        super();
    }
}
