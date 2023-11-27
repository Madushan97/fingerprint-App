package com.cba.mpos.aoer.controller;

import com.cba.mpos.aoer.model.external.TargetEntity;
import com.cba.mpos.aoer.model.internal.SourceEntity;
import com.cba.mpos.aoer.service.common.DataTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataTransferController {

    private final DataTransferService dataTransferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferData() {
        dataTransferService.transferData();
        return ResponseEntity.ok("Data transfer successful");
    }

    @GetMapping("/target/getAll")
    public ResponseEntity<List<TargetEntity>> getAllTarget() {
        List<TargetEntity> targetData = dataTransferService.getAllTargetData();
        return new ResponseEntity<>(targetData, HttpStatus.OK);
    }

    @GetMapping("/source/getAll")
    public ResponseEntity<List<SourceEntity>> getAllSource() {
        List<SourceEntity> targetData = dataTransferService.getAllSourceData();
        return new ResponseEntity<>(targetData, HttpStatus.OK);
    }
}
