package com.example.demo.pojos.inputs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MeetingINP {
    @JsonProperty("socio")
    @With
    private UUID partnerId;

    @JsonProperty("asistencia")
    @With
    private Boolean presence;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("fechaReunion")
    @With
    private Date meetingDate;
}
