//package com.cba.mpos.aoer.service;
//
//import com.cba.mpos.aoer.dto.DeviceDto;
//import com.cba.mpos.aoer.dto.ReportReq;
//import com.cba.mpos.aoer.model.internal.PaperRollStatus;
//
//import java.util.List;
//import java.util.Map;
//
//public interface AppOperationService {
//    String notifyPaperRollStatus(String serialNo, String paperStatus);
//
//    List<PaperRollStatus> getAllPaperRollStatus();
//
//    Map<String, Object> getPaperStatusCountForPeriod(ReportReq reportReq);
//
//    String notifyOutOfGeoFence(DeviceDto deviceDto);
//
//    void checkUnsettledTerminalsAndNotify() throws Exception;
//
//    void checkInactiveTerminalsAndNotify() throws Exception;
//
//    void checkTranAmountAndNotify() throws Exception;
//
//    void checkTranVelocityAndNotify() throws Exception;
//
//    void checkFailedTranAndNotify() throws Exception;
//
//    void checkTimeoutTranAndNotify() throws Exception;
//
//    Map<String, Integer> getDeviceStatus() throws Exception;
//
//    Map<String, Object> getTranStatusForPeriod(ReportReq reportReq) throws Exception;
//}
