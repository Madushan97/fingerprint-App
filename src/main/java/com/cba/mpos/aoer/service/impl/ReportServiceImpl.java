//package com.cba.mpos.aoer.service.impl;
//
//import com.cba.mpos.aoer.dto.DeviceDto;
//import com.cba.mpos.aoer.dto.ReportReq;
//import com.cba.mpos.aoer.dto.TerminalDto;
//import com.cba.mpos.aoer.dto.TransactionDto;
//import com.cba.mpos.aoer.exception.NotFoundException;
//import com.cba.mpos.aoer.model.external.Merchants;
//import com.cba.mpos.aoer.model.external.Terminals;
//import com.cba.mpos.aoer.model.internal.TaskConfig;
//import com.cba.mpos.aoer.repository.external.DeviceRepository;
//import com.cba.mpos.aoer.repository.external.MerchantRepository;
//import com.cba.mpos.aoer.repository.external.TerminalRepository;
//import com.cba.mpos.aoer.repository.external.TranSummaryRepository;
//import com.cba.mpos.aoer.repository.internal.EmailConfigRepository;
//import com.cba.mpos.aoer.repository.internal.TaskConfigRepository;
//import com.cba.mpos.aoer.service.ReportService;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//@Service
//public class ReportServiceImpl implements ReportService {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
//
//    private final DeviceRepository deviceRepository;
//    private final TranSummaryRepository tranSummaryRepository;
//    private final MerchantRepository merchantRepository;
//    private final TerminalRepository terminalRepository;
//    private final TaskConfigRepository taskConfigRepository;
//
//    public ReportServiceImpl(DeviceRepository deviceRepository, TranSummaryRepository tranSummaryRepository,
//                             MerchantRepository merchantRepository, TerminalRepository terminalRepository,
//                             EmailConfigRepository emailConfigRepository, TaskConfigRepository taskConfigRepository) {
//        this.deviceRepository = deviceRepository;
//        this.tranSummaryRepository = tranSummaryRepository;
//        this.merchantRepository = merchantRepository;
//        this.terminalRepository = terminalRepository;
//        this.taskConfigRepository = taskConfigRepository;
//    }
//
//    @Override
//    @Transactional
//    public List<TerminalDto> getSalesVolumeReportData(ReportReq reportReq) throws Exception {
//        List<TerminalDto> terminalDtoList = new ArrayList<>();
//        terminalRepository.findByDeletedRecOrderByMerchantsAsc(0).forEach(terminal -> {
//            Integer totalAmount = tranSummaryRepository.getTotalAmountByTidAndTranTypeForPeriod(terminal.getTerminalId(),
//                    "sale", reportReq.getFrom(), reportReq.getTo());
//            totalAmount = totalAmount != null ? totalAmount : 0;
//            TerminalDto terminalDto = new TerminalDto();
//            terminalDto.setTid(terminal.getTerminalId());
//            terminalDto.setMid(terminal.getMerchants().getMerchantId());
//            terminalDto.setMerchantName(terminal.getMerchants().getName());
//            terminalDto.setAmount(new BigDecimal(totalAmount).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
//            terminalDtoList.add(terminalDto);
//        });
//        if (!terminalDtoList.isEmpty()) {
//            return terminalDtoList;
//        } else {
//            throw new NotFoundException("No data found for given period.");
//        }
//    }
//
//    @Override
//    @Transactional
//    public byte[] generateSalesVolumeReport(ReportReq reportReq) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("/reports/sales_volume.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//        JRBeanCollectionDataSource beanColDataSource =
//                new JRBeanCollectionDataSource(getSalesVolumeReportData(reportReq));
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("date", new Date());
//        parameters.put("from", reportReq.getFrom());
//        parameters.put("to", reportReq.getTo());
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
//
//    @Override
//    @Transactional
//    public List<TerminalDto> getLowTurnoverReportData(ReportReq reportReq) throws Exception {
//        List<TerminalDto> terminalDtoList = new ArrayList<>();
//        terminalRepository.findByDeletedRecOrderByMerchantsAsc(0).forEach(terminal -> {
//            Integer totalAmount = tranSummaryRepository.getTotalAmountByTidAndTranTypeForPeriod(terminal.getTerminalId(),
//                    "sale", reportReq.getFrom(), reportReq.getTo());
//            totalAmount = totalAmount != null ? totalAmount : 0;
//            //TODO check target amount
//            if (totalAmount < 50000000) {
//                TerminalDto terminalDto = new TerminalDto();
//                terminalDto.setTid(terminal.getTerminalId());
//                terminalDto.setMid(terminal.getMerchants().getMerchantId());
//                terminalDto.setMerchantName(terminal.getMerchants().getName());
//                terminalDto.setAmount(new BigDecimal(totalAmount).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
//                terminalDto.setTargetAmount(new BigDecimal(50000000).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
//                terminalDtoList.add(terminalDto);
//            }
//        });
//        if (!terminalDtoList.isEmpty()) {
//            return terminalDtoList;
//        } else {
//            throw new NotFoundException("No data found for given period.");
//        }
//    }
//
//    @Override
//    @Transactional
//    public byte[] generateLowTurnoverReport(ReportReq reportReq) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("/reports/low_turnover.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//        JRBeanCollectionDataSource beanColDataSource =
//                new JRBeanCollectionDataSource(getLowTurnoverReportData(reportReq));
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("date", new Date());
//        parameters.put("from", reportReq.getFrom());
//        parameters.put("to", reportReq.getTo());
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
//
//    @Override
//    @Transactional
//    public List<DeviceDto> getActiveTerminalReportData(ReportReq reportReq) throws Exception {
//        List<DeviceDto> activeDeviceDtoList = new ArrayList<>();
//        deviceRepository.findByDeletedRec(0).forEach(device -> {
//            Set<Terminals> terminalList = device.getTerminalses();
//            Merchants merchant = null;
//            long count = 0;
//            for (Terminals terminal : terminalList) {
//                count += tranSummaryRepository.getCountByTidForPeriod(terminal.getTerminalId(), reportReq.getFrom(),
//                        reportReq.getTo());
//                if (merchant == null) {
//                    merchant = terminal.getMerchants();
//                }
//            }
//            if (count > 0) {
//                DeviceDto deviceDto = new DeviceDto();
//                deviceDto.setMid(merchant.getMerchantId());
//                deviceDto.setMerchantName(merchant.getName());
//                deviceDto.setSerialNo(device.getSerialNo());
//                deviceDto.setTranCount(count);
//                activeDeviceDtoList.add(deviceDto);
//            }
//        });
//        if (!activeDeviceDtoList.isEmpty()) {
//            return activeDeviceDtoList;
//        } else {
//            throw new NotFoundException("No data found for given period.");
//        }
//    }
//
//    @Override
//    @Transactional
//    public byte[] generateActiveTerminalReport(ReportReq reportReq) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("/reports/active_terminals.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//        JRBeanCollectionDataSource beanColDataSource =
//                new JRBeanCollectionDataSource(getActiveTerminalReportData(reportReq));
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("date", new Date());
//        parameters.put("from", reportReq.getFrom());
//        parameters.put("to", reportReq.getTo());
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
//
//    @Override
//    @Transactional
//    public List<DeviceDto> getInactiveTerminalReportData(ReportReq reportReq) throws Exception {
//        List<DeviceDto> deviceDtoList = new ArrayList<>();
//        deviceRepository.findByDeletedRec(0).forEach(device -> {
//            Set<Terminals> terminalList = device.getTerminalses();
//            Merchants merchant = null;
//            long count = 0;
//            if (!terminalList.isEmpty()) {
//                for (Terminals terminal : terminalList) {
//                    count += tranSummaryRepository.getCountByTidForPeriod(terminal.getTerminalId(),
//                            reportReq.getFrom(), reportReq.getTo());
//                    if (merchant == null) {
//                        merchant = terminal.getMerchants();
//                    }
//                }
//                if (count == 0) {
//                    DeviceDto deviceDto = new DeviceDto();
//                    deviceDto.setMid(Objects.requireNonNull(merchant).getMerchantId());
//                    deviceDto.setMerchantName(merchant.getName());
//                    deviceDto.setSerialNo(device.getSerialNo());
//                    deviceDto.setTranCount(count);
//                    deviceDtoList.add(deviceDto);
//                }
//            }
//        });
//        if (!deviceDtoList.isEmpty()) {
//            return deviceDtoList;
//        } else {
//            throw new NotFoundException("No data found for given period.");
//        }
//    }
//
//    @Override
//    @Transactional
//    public byte[] generateInactiveTerminalReport(ReportReq reportReq) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("/reports/inactive_terminals.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//        JRBeanCollectionDataSource beanColDataSource =
//                new JRBeanCollectionDataSource(getInactiveTerminalReportData(reportReq));
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("date", new Date());
//        parameters.put("from", reportReq.getFrom());
//        parameters.put("to", reportReq.getTo());
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
//
//    @Override
//    @Transactional
//    public List<TransactionDto> getHighAmountTransactionsReportData(ReportReq reportReq) throws Exception {
//        TaskConfig taskConfig = taskConfigRepository.findByAction("TRAN_AMOUNT");
//        List<TransactionDto> transactionDtoList = new ArrayList<>();
//        tranSummaryRepository.getAllByTranTypeAndAmountGrtThanForPeriod("sale", taskConfig.getValue(),
//                        reportReq.getFrom(), reportReq.getTo()).forEach(tranSummary -> {
//            Merchants merchant = merchantRepository.findByMerchantId(tranSummary.getMid());
//            TransactionDto transactionDto = new TransactionDto();
//            transactionDto.setTid(tranSummary.getTid());
//            transactionDto.setMid(tranSummary.getMid());
//            transactionDto.setMerchantName(merchant.getName());
//            transactionDto.setAmount(new BigDecimal(tranSummary.getAmount()).divide(new BigDecimal(100), 2,
//                    RoundingMode.HALF_UP));
//            transactionDto.setTimestamp(tranSummary.getDateTime()
//                    .format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss")));
//            transactionDtoList.add(transactionDto);
//        });
//        if (!transactionDtoList.isEmpty()) {
//            return transactionDtoList;
//        } else {
//            throw new NotFoundException("No data found for given period.");
//        }
//    }
//
//    @Override
//    @Transactional
//    public byte[] generateHighAmountTransactionsReport(ReportReq reportReq) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("/reports/high_amount_transactions.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//        JRBeanCollectionDataSource beanColDataSource =
//                new JRBeanCollectionDataSource(getHighAmountTransactionsReportData(reportReq));
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("date", new Date());
//        parameters.put("from", reportReq.getFrom());
//        parameters.put("to", reportReq.getTo());
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
//
//    @Override
//    @Transactional
//    public List<TerminalDto> getTransactionsVelocityReportData(ReportReq reportReq) throws Exception {
//        List<TerminalDto> terminalDtoList = new ArrayList<>();
//        terminalRepository.findByDeletedRec(0).forEach(terminal -> {
//            TerminalDto terminalDto = new TerminalDto();
//            terminalDto.setTid(terminal.getTerminalId());
//            terminalDto.setMid(terminal.getMerchants().getMerchantId());
//            terminalDto.setMerchantName(terminal.getMerchants().getName());
//            terminalDto.setTranCount(tranSummaryRepository.getCountByTidForPeriod(terminal.getTerminalId(),
//                    reportReq.getFrom(), reportReq.getTo()));
//            terminalDtoList.add(terminalDto);
//        });
//        if (!terminalDtoList.isEmpty()) {
//            return terminalDtoList;
//        } else {
//            throw new NotFoundException("No data found for given period.");
//        }
//    }
//
//    @Override
//    @Transactional
//    public byte[] generateTransactionsVelocityReport(ReportReq reportReq) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("/reports/transaction_velocity.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//        JRBeanCollectionDataSource beanColDataSource = new
//                JRBeanCollectionDataSource(getTransactionsVelocityReportData(reportReq));
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("date", new Date());
//        parameters.put("from", reportReq.getFrom());
//        parameters.put("to", reportReq.getTo());
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }
//}
