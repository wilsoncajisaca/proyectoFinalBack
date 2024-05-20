package com.example.demo.pojos.outputs;

import lombok.*;

@Builder
@Data
public class PartnerWithPaidDTO {
    private String partnerId;
    private String identification;
    private String names;
    private String lastname;
    private String phone;
    private Integer gender;
    private Integer paid;
}