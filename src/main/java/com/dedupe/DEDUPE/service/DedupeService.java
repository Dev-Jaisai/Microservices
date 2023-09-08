package com.dedupe.DEDUPE.service;

import com.DDE.entity.DdeData;
import com.DDE.service.DdeDataService;
import com.dedupe.DEDUPE.dto.DedupeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class DedupeService {

    @Autowired
    DdeDataService ddeDataService;

    @KafkaListener(topics = "user-data-topic", groupId = "dedupe-service")
    public DedupeDto processUserData(String panCard) {
        DedupeDto dedupeDto = new DedupeDto();
        dedupeDto.setPancard(panCard);

        // Check if the PAN is already in the DDE
        DdeData ddeDataFromDde = ddeDataService.getDdeDataByPan(panCard);

        if (ddeDataFromDde != null) {
            // If PAN is found in DDE, populate dedupeDto with the DDE data
            dedupeDto.setId(ddeDataFromDde.getId());
            dedupeDto.setLastName(ddeDataFromDde.getLastName());
            dedupeDto.setFirstName(ddeDataFromDde.getFirstName());
            dedupeDto.setMobileNumber(ddeDataFromDde.getMobileNumber());
        }

        return dedupeDto;
    }
}
