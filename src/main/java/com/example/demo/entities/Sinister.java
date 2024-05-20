package com.example.demo.entities;

import com.example.demo.entities.auditoria.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "sinister")
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Sinister extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_sinister",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "sinister_id", updatable = false, nullable = false)
    private UUID sinisterId;

    @Column(name = "socio_id")
    @With
    private UUID partnerId;
    @JsonIgnore
    @JoinColumn(name = "socio_id",
            nullable = false,
            referencedColumnName = "socio_id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_sinister_partner"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Partner partner;

    @Column(name = "sinister_type_id")
    @With
    private UUID sinisterTypeId;
    @JsonIgnore
    @JoinColumn(name = "sinister_type_id",
            nullable = false,
            referencedColumnName = "sinister_type_id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_sinister_sinister_type"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SinisterType sinisterType;

    @Column(name = "location", length = 250, nullable = false)
    private String location;

    @Column(name = "description", length = 250)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sinister")
    private Set<Photo> photos = new HashSet<>();

    @Tolerate
    public Sinister() {
        super();
    }
}
