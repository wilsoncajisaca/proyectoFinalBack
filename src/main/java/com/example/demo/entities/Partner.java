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
@Table(name = "socios")
public class Partner extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_socio",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "socio_id", updatable = false, nullable = false)
    private UUID partnerId;

    @Column(name = "identificacion", length = 10, nullable = false, unique = true)
    @With
    private String identification;

    @Column(name = "nombre", length = 100, nullable = false)
    @With
    private String names;

    @Column(name = "apellido", length = 100, nullable = false)
    @With
    private String lastname;

    @Column(name = "direccion", length = 50)
    @With
    private String address;

    @Column(name = "email", length = 250, unique = true)
    @With
    private String email;

    @Column(name = "telefono", length = 10)
    @With
    private String phone;

    @Column(name = "genero", length = 10)
    @With
    private Integer gender;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partner")
    private Set<Auth> auths = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partner")
    private Set<Sanction> sanctions = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "partner")
    private Set<Car> cars = new HashSet<>();

    @Tolerate
    public Partner() {
        super();
    }
}
