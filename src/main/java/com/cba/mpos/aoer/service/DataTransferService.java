package com.cba.mpos.aoer.service;

import com.cba.mpos.aoer.model.external.TargetEntity;
import com.cba.mpos.aoer.model.internal.SourceEntity;
import com.cba.mpos.aoer.repository.external.TargetRepository;
import com.cba.mpos.aoer.repository.internal.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataTransferService {

    private final SourceRepository sourceRepository;
    private final TargetRepository targetRepository;

    public List<TargetEntity> getAllTargetData() {
        try {
            return targetRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<SourceEntity> getAllSourceData() {
        try {
            return sourceRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public void transferData() {

        List<SourceEntity> dataToTransfer = sourceRepository.findAll();
        List<TargetEntity> destinationData = convertToDestinationEntity(dataToTransfer);
        targetRepository.saveAll(destinationData);
    }

    private List<TargetEntity> convertToDestinationEntity(List<SourceEntity> sourceData) {
        if (sourceData == null || sourceData.isEmpty()) {
            return Collections.emptyList();
        }

        return sourceData.stream()
                .map(this::mapSourceToTarget)
                .collect(Collectors.toList());
    }

    private TargetEntity mapSourceToTarget(SourceEntity sourceEntity) {
        TargetEntity targetEntity = new TargetEntity();
        LocalDateTime localDateTime = sourceEntity.getLogDateTime().toLocalDateTime();
        System.out.println("SourceEntity before save: " + sourceEntity);
        if (sourceEntity.getIsProcessed() == 0) {
            targetEntity.setDate(localDateTime.toLocalDate());          // date
            targetEntity.setTime(localDateTime.toLocalTime());          // time
            targetEntity.setEmployee_id(sourceEntity.getEmpID());       // employee_id
            targetEntity.setAction(String.valueOf(1));                  // action
            targetEntity.setAction_hbx(String.valueOf(0));              // action_hbx
            targetEntity.setCheck(String.valueOf(0));                   // check
            targetEntity.setStatus(String.valueOf(sourceEntity.getIsProcessed()));  // is processed

            sourceEntity.setIsProcessed((byte) 1);      // set isProcessed to 1 before saving
        }
        sourceRepository.save(sourceEntity);
        System.out.println("SourceEntity after save: " + sourceEntity);
        return targetEntity;
    }


    /*
    private String convertDateToDateString(LocalDate date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private String convertDateToTimeString(LocalDateTime date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }
    */

}
