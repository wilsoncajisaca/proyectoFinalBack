package com.example.demo.repositories;

import com.example.demo.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPartnerRepository extends JpaRepository<Partner, Serializable> {
    Optional<Partner> findByIdentificationAndStatus(String identification, Boolean status);

    @Query(value = "select" +
            " s.socio_id partnerId,"+
            " s.identificacion identification," +
            " s.nombre name," +
            " s.apellido lastname," +
            " s.telefono phone," +
            " s.genero gender," +
            " case" +
            "  when count(multa_id) > 0 then 1" +
            "  else 0" +
            " end paid" +
            " from" +
            " socios s" +
            " left join multas m" +
            " on" +
            " s.socio_id = m.socio_id" +
            " and m.pagado = 0" +
            " group by" +
            " s.socio_id," +
            " s.identificacion," +
            " s.nombre," +
            " s.apellido," +
            " s.telefono," +
            " s.genero;", nativeQuery = true)
    List<Object[]> findPartnersWithPaid();
}
