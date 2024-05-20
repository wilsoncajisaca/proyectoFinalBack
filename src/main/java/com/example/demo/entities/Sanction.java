package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Entity
@Table(name = "multas")
public class Sanction extends Audit implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "pk_multa",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "multa_id", updatable = false, nullable = false)
    private UUID sanctionId;

    @Column(name = "socio_id")
    @With
    private UUID partnerId;
    @JsonIgnore
    @JoinColumn(name = "socio_id",
            referencedColumnName = "socio_id",
            insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_multa_socio"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Partner partner;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_multa")
    @With
    private Date sanctionDate;

    @Column(name = "valor")
    @With
    private Double value;

    @Column(name = "pagado", length = 1)
    @With
    private Integer paid;
    @Tolerate
    public Sanction (){
        super();
    }
}