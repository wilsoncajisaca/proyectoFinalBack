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
@Table(name = "photo")
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Photo extends Audit implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_photo",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "photo_id", updatable = false, nullable = false)
    private UUID photoId;

    @Column(name = "url", length = 300)
    private String url;

    @Column(name = "sinister_id")
    private UUID sinisterId;
    @JoinColumn(name = "sinister_id",
            nullable = false,
            referencedColumnName = "sinister_id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_phone_sinister"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sinister sinister;

    @Column(name = "status")
    private Boolean status;

    @PrePersist
    public void prePersist() {
        this.status = true;
    }

    @Tolerate
    public Photo() {
        super();
    }
}