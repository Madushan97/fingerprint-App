//package com.cba.mpos.aoer.service;
//
//import com.cba.mpos.aoer.dto.DeviceDto;
//import com.cba.mpos.aoer.dto.ReportReq;
//import com.cba.mpos.aoer.dto.TerminalDto;
//import com.cba.mpos.aoer.dto.TransactionDto;
//
//import java.util.List;
//
//public interface ReportService {
//
//    List<TerminalDto> getSalesVolumeReportData(ReportReq reportReq) throws Exception;
//
//    byte[] generateSalesVolumeReport(ReportReq reportReq) throws Exception;
//
//    List<TerminalDto> getLowTurnoverReportData(ReportReq reportReq) throws Exception;
//
//    byte[] generateLowTurnoverReport(ReportReq reportReq) throws Exception;
//
//    List<DeviceDto> getActiveTerminalReportData(ReportReq reportReq) throws Exception;
//
//    byte[] generateActiveTerminalReport(ReportReq reportReq) throws Exception;
//
//    List<DeviceDto> getInactiveTerminalReportData(ReportReq reportReq) throws Exception;
//
//    byte[] generateInactiveTerminalReport(ReportReq reportReq) throws Exception;
//
//    List<TransactionDto> getHighAmountTransactionsReportData(ReportReq reportReq) throws Exception;
//
//    byte[] generateHighAmountTransactionsReport(ReportReq reportReq) throws Exception;
//
//    List<TerminalDto> getTransactionsVelocityReportData(ReportReq reportReq) throws Exception;
//
//    byte[] generateTransactionsVelocityReport(ReportReq reportReq) throws Exception;
//
//}
