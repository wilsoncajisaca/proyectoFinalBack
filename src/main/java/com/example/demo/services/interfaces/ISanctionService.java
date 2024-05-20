package com.example.demo.services.interfaces;

import com.example.demo.entities.Sanction;
import com.example.demo.exception.GeneralException;
import com.example.demo.pojos.inputs.MeetingINP;
import com.example.demo.pojos.inputs.SanctionINP;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ISanctionService {
    List<Sanction> getAllSanctionByPartner(UUID partnerId) throws GeneralException;
    void createSanction(List<SanctionINP> sanctionINP) throws GeneralException;
    Map<String,String> meetingList(List<MeetingINP> meetingINPS) throws GeneralException;
    Map<String, String> paySantion(UUID sanctionId) throws GeneralException;
    Map<String, String> payAllSantion(List<SanctionINP> sanctionINPs) throws GeneralException;
}
