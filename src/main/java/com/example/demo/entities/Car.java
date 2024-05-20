package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.demo.entities.auditoria.Audit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "vehiculo")
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Car extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_vehiculo",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "vehiculo_id", updatable = false, nullable = false)
    private UUID carId;

    @Column(name = "socio_id")
    @With
    private UUID partnerId;
    @JsonIgnore
    @JoinColumn(name = "socio_id",
            nullable = false,
            referencedColumnName = "socio_id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_vehiculo_socio"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Partner partner;

    @Column(name = "placa", length = 150)
    @With
    private String vehicleRegistration;

    @Column(name = "marca", length = 150)
    @With
    private String name;

    @Column(name = "modelo", length = 150)
    @With
    private String model;

    @Column(name = "disco", length = 150)
    @With
    private Integer disco;
}