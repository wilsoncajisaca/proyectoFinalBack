package com.example.demo.entities.auditoria;

import com.example.demo.security.UserAuthentication;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Audit implements Serializable {

    private Date creadoEn;
    private String creadoPor;
    private Date actualizadoEn;
    private String actualizadoPor;
    private Boolean status;

    @PrePersist
    public void prePersist() {
        creadoEn = new Date();
        creadoPor = UserAuthentication.getUsername();
        status = true;
    }

    @PreUpdate
    public void preUpdate() {
        actualizadoEn = new Date();
        actualizadoPor = UserAuthentication.getUsername();
    }

    @Tolerate
    public Audit(){
        super();
    }
}
