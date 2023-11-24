package com.cba.mpos.aoer.controller;

import com.cba.mpos.aoer.dto.DeviceDto;
import com.cba.mpos.aoer.dto.ReportReq;
import com.cba.mpos.aoer.model.internal.PaperRollStatus;
import com.cba.mpos.aoer.service.AppOperationService;
import com.cba.mpos.aoer.utils.RestApiV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RestApiV1
public class AppOperationController {

    @Autowired
    AppOperationService appOperationService;

    @PostMapping(path = "/paper-roll-status")
    public ResponseEntity<String> notifyPaperStatus(@RequestParam(value = "serial") String serial,
                                                    @RequestParam(value = "status") String status) throws Exception {
        return new ResponseEntity<>(appOperationService.notifyPaperRollStatus(serial, status), HttpStatus.OK);
    }

    @GetMapping(path = "/paper-roll-status")
    public ResponseEntity<List<PaperRollStatus>> getAllPaperStatus() throws Exception {
        return new ResponseEntity<>(appOperationService.getAllPaperRollStatus(), HttpStatus.OK);
    }

    @PostMapping(path = "/paper-roll-status/highlight")
    public ResponseEntity<Map<String, Object>> getPaperStatusCount(@RequestBody ReportReq reportReq) throws Exception {
        return new ResponseEntity<>(appOperationService.getPaperStatusCountForPeriod(reportReq), HttpStatus.OK);
    }

    @PostMapping(path = "/out-of-geo-fence")
    public ResponseEntity<String> notifyOutOfGeoFence(@RequestBody DeviceDto deviceDto) throws Exception {
        return new ResponseEntity<>(appOperationService.notifyOutOfGeoFence(deviceDto), HttpStatus.OK);
    }

    @GetMapping(path = "/device-status")
    public ResponseEntity<Map<String, Integer>> getDeviceStatus() throws Exception {
        return new ResponseEntity<>(appOperationService.getDeviceStatus(), HttpStatus.OK);
    }

    @PostMapping(path = "/transaction-status")
    public ResponseEntity<Map<String, Object>> getTranStatus(@RequestBody ReportReq reportReq) throws Exception {
        return new ResponseEntity<>(appOperationService.getTranStatusForPeriod(reportReq), HttpStatus.OK);
    }
}
