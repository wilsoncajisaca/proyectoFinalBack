package com.example.demo.services.interfaces;

import com.example.demo.entities.Partner;
import com.example.demo.pojos.inputs.PartnerINP;
import com.example.demo.pojos.outputs.PartnerWithPaidDTO;

import java.util.List;
import java.util.Map;

public interface IPartnerService {
    Partner getByIdentification(String identification) throws RuntimeException;
    List<PartnerWithPaidDTO> getAllPartner() throws RuntimeException;

    void createPartner(PartnerINP partnerINP) throws RuntimeException;

    Partner updatePartner(PartnerINP partnerINP)throws RuntimeException;
}
