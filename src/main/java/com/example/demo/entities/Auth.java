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
@Entity
@Table(name = "autenticacion", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}) })
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Auth extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_autenticacion",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "autenticacion_id", updatable = false, nullable = false)
    private UUID authId;

    @Column(name = "socio_id")
    @With
    private UUID partnerId;
    @JsonIgnore
    @JoinColumn(name = "socio_id",
            nullable = false,
            referencedColumnName = "socio_id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_autenticacion_partner"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Partner partner;

    @Column(name = "username", length = 150, unique = true)
    @With
    private String username;

    @Column(name = "password", length = 150)
    @With
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "autenticacion_rol",
            joinColumns = @JoinColumn(name = "autenticacion_id", referencedColumnName = "autenticacion_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_socio_id",referencedColumnName = "rol_socio_id"))
    @With
    private Set<RolPartner> roles = new HashSet<>();

    @Tolerate
    public Auth() {
        super();
    }
}
