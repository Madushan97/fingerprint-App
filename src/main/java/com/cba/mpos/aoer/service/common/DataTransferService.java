package com.cba.mpos.aoer.service.common;

import com.cba.mpos.aoer.model.external.TargetEntity;
import com.cba.mpos.aoer.model.internal.SourceEntity;
import com.cba.mpos.aoer.repository.external.TargetRepository;
import com.cba.mpos.aoer.repository.internal.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        return targetRepository.findAll();
    }

    public List<SourceEntity> getAllSourceData() {
        return sourceRepository.findAll();
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

        targetEntity.setDateIndex(convertDateToDateString(sourceEntity.getLogDateTime()));
        targetEntity.setTimeIndex(convertDateToTimeString(sourceEntity.getLogDateTime()));
        targetEntity.setEmployeeIdIndex(sourceEntity.getEmpID());
        targetEntity.setId(sourceEntity.getLogID());
        targetEntity.setAction("1");
        targetEntity.setActionHbx("0");
        targetEntity.setStatus(String.valueOf(sourceEntity.getIsProcessed()));
        targetEntity.setCheck(sourceEntity.getCheckType());
//        private Timestamp createdTime;
//        private Timestamp updateTime;

        return targetEntity;
    }

    private String convertDateToDateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private String convertDateToTimeString(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }


}
