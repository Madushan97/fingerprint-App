//package com.cba.mpos.aoer.controller;
//
//import com.cba.mpos.aoer.dto.DeviceDto;
//import com.cba.mpos.aoer.dto.ReportReq;
//import com.cba.mpos.aoer.dto.TerminalDto;
//import com.cba.mpos.aoer.dto.TransactionDto;
//import com.cba.mpos.aoer.service.ReportService;
//import com.cba.mpos.aoer.utils.RestApiV1;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//
//@RestApiV1
//public class ReportController {
//
//    @Autowired
//    ReportService reportService;
//
//    @PostMapping(path = "/sales-volume-reports")
//    public ResponseEntity<byte[]> generateSalesVolumeReport(@RequestBody ReportReq reportReq) throws Exception {
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                .header("Content-Disposition", "inline; filename=sale_volume_report.pdf")
//                .body(reportService.generateSalesVolumeReport(reportReq));
//    }
//
//    @PostMapping(path = "/sales-volume-reports/data")
//    public ResponseEntity<List<TerminalDto>> getSalesVolumeReportData(@RequestBody ReportReq reportReq) throws Exception {
//        return new ResponseEntity<>(reportService.getSalesVolumeReportData(reportReq), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/low-turnover-reports")
//    public ResponseEntity<byte[]> generateLowTurnoverReport(@RequestBody ReportReq reportReq) throws Exception {
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                .header("Content-Disposition", "inline; filename=low_turnover_report.pdf")
//                .body(reportService.generateLowTurnoverReport(reportReq));
//    }
//
//    @PostMapping(path = "/low-turnover-reports/data")
//    public ResponseEntity<List<TerminalDto>> getLowTurnoverReportData(@RequestBody ReportReq reportReq) throws Exception {
//        return new ResponseEntity<>(reportService.getLowTurnoverReportData(reportReq), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/active-terminal-reports")
//    public ResponseEntity<byte[]> generateActiveTerminalReport(@RequestBody ReportReq reportReq) throws Exception {
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                .header("Content-Disposition", "inline; filename=active_terminal_report.pdf")
//                .body(reportService.generateActiveTerminalReport(reportReq));
//    }
//
//    @PostMapping(path = "/active-terminal-reports/data")
//    public ResponseEntity<List<DeviceDto>> getActiveTerminalReportData(@RequestBody ReportReq reportReq) throws Exception {
//        return new ResponseEntity<>(reportService.getActiveTerminalReportData(reportReq), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/inactive-terminal-reports")
//    public ResponseEntity<byte[]> generateInactiveTerminalReport(@RequestBody ReportReq reportReq) throws Exception {
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                .header("Content-Disposition", "inline; filename=inactive_terminal_report.pdf")
//                .body(reportService.generateInactiveTerminalReport(reportReq));
//    }
//
//    @PostMapping(path = "/inactive-terminal-reports/data")
//    public ResponseEntity<List<DeviceDto>> getInactiveTerminalReportData(@RequestBody ReportReq reportReq)
//            throws Exception {
//        return new ResponseEntity<>(reportService.getInactiveTerminalReportData(reportReq), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/high-amount-transactions-reports")
//    public ResponseEntity<byte[]> generateHighAmountTransactionsReport(@RequestBody ReportReq reportReq) throws Exception {
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                .header("Content-Disposition", "inline; filename=high_amount_transactions_report.pdf")
//                .body(reportService.generateHighAmountTransactionsReport(reportReq));
//    }
//
//    @PostMapping(path = "/high-amount-transactions-reports/data")
//    public ResponseEntity<List<TransactionDto>> getHighAmountTransactionsReportData(@RequestBody ReportReq reportReq)
//            throws Exception {
//        return new ResponseEntity<>(reportService.getHighAmountTransactionsReportData(reportReq), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/transactions-velocity-reports")
//    public ResponseEntity<byte[]> generateTransactionsVelocityReport(@RequestBody ReportReq reportReq) throws Exception {
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/pdf; charset=UTF-8")
//                .header("Content-Disposition", "inline; filename=high_amount_transactions_report.pdf")
//                .body(reportService.generateTransactionsVelocityReport(reportReq));
//    }
//
//    @PostMapping(path = "/transactions-velocity-reports/data")
//    public ResponseEntity<List<TerminalDto>> getTransactionsVelocityReportData(@RequestBody ReportReq reportReq) throws Exception {
//        return new ResponseEntity<>(reportService.getTransactionsVelocityReportData(reportReq), HttpStatus.OK);
//    }
//}
