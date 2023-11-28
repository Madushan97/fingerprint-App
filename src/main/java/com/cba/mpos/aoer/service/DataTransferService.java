package com.cba.mpos.aoer.service;

import com.cba.mpos.aoer.model.external.TargetEntity;
import com.cba.mpos.aoer.model.internal.CheckInOut;
import com.cba.mpos.aoer.model.internal.SourceEntity;
import com.cba.mpos.aoer.repository.external.TargetRepository;
import com.cba.mpos.aoer.repository.internal.CheckInOutRepository;
import com.cba.mpos.aoer.repository.internal.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataTransferService {

    private final SourceRepository sourceRepository;
    private final TargetRepository targetRepository;
    private final CheckInOutRepository checkInOutRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DataTransferService.class);

    /*
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
    */

public void transferData() {
    //List<SourceEntity> dataToTransfer = sourceRepository.findByIsProcessedEquals((byte) 0);
    List<SourceEntity> dataToTransfer =sourceRepository.findAll();
    if (dataToTransfer != null && !dataToTransfer.isEmpty()) {
        List<TargetEntity> destinationData = convertToDestinationEntity(dataToTransfer);
        if(destinationData!=null && destinationData.size()>0 )
            targetRepository.saveAll(destinationData);
    }
}

    private List<TargetEntity> convertToDestinationEntity(List<SourceEntity> sourceData) {
        if (sourceData == null || sourceData.isEmpty()) {
            LOGGER.info("No source data available for conversion");
            return null;
        }

        List<TargetEntity> tt = new ArrayList<>();
        for (SourceEntity tr:sourceData) {
            TargetEntity targetEntity = mapSourceToTarget(tr);
            if(targetEntity!=null){
                tt.add(targetEntity);
            }
        }
        return tt;
    }

    TargetEntity mapSourceToTarget(SourceEntity sourceEntity) {

        TargetEntity targetEntity = new TargetEntity();
        LocalDateTime localDateTime = sourceEntity.getLogDateTime().toLocalDateTime();
        System.out.println("SourceEntity before save: " + sourceEntity);
        if (sourceEntity.getIsProcessed() == false) {
            targetEntity.setDate(localDateTime.toLocalDate());
            targetEntity.setTime(localDateTime.toLocalTime());
            targetEntity.setEmployee_id(sourceEntity.getEmpID());
            targetEntity.setAction(String.valueOf(1));
            targetEntity.setAction_hbx(String.valueOf(0));
            targetEntity.setCheck(String.valueOf(0));
            targetEntity.setStatus(String.valueOf(sourceEntity.getIsProcessed()));

            // After pushing the data, IsProcessed should be updated
            try {
                Optional<CheckInOut> checkInOutOptional = checkInOutRepository.findById((int) sourceEntity.getLogID());

                if (checkInOutOptional.isPresent()) {
                    CheckInOut checkInOut = checkInOutOptional.get();
                    checkInOut.setIsProcessed((byte) 1);
                    checkInOutRepository.save(checkInOut);

                } else {
                    throw new IllegalStateException("CheckInOut record not found for LogID: " + sourceEntity.getLogID());
                }
            } catch (Exception e) {
                LOGGER.error("Error updating CheckInOut record for LogID: " + sourceEntity.getLogID(), e);
                throw new RuntimeException("Error updating CheckInOut record");
            }
            System.out.println("SourceEntity after save: " + sourceEntity);
            return targetEntity;
        }
        else{
            return null;
        }
    }
}
