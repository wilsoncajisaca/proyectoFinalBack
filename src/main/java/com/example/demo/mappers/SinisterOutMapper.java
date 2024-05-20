package com.example.demo.mappers;

import com.example.demo.entities.Photo;
import com.example.demo.entities.Sinister;
import com.example.demo.pojos.outputs.SinisterOUT;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class SinisterOutMapper {
    public static SinisterOUT toOutput(Sinister sinister, Set<String> urlPhotos) {
        // Formatear la fecha
        Instant instant = sinister.getCreadoEn().toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String formattedDate = localDate.format(formatter);

        return SinisterOUT.builder()
                .siniestroId(sinister.getSinisterId())
                .urlFotos(urlPhotos)
                .ubicacion(sinister.getLocation())
                .observacion(sinister.getDescription())
                .tipoSiniestro(sinister.getSinisterType().getName())
                .fechaSiniestro(formattedDate)
                .build();
    }
}
