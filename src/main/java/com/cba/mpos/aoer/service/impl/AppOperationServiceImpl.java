package com.cba.mpos.aoer.service.impl;

import com.cba.mpos.aoer.dto.*;
import com.cba.mpos.aoer.exception.NotFoundException;
import com.cba.mpos.aoer.model.external.*;
import com.cba.mpos.aoer.model.internal.EmailConfig;
import com.cba.mpos.aoer.model.internal.PaperRollStatus;
import com.cba.mpos.aoer.model.internal.TaskConfig;
import com.cba.mpos.aoer.repository.external.*;
import com.cba.mpos.aoer.repository.internal.EmailConfigRepository;
import com.cba.mpos.aoer.repository.internal.PaperRollStatusRepository;
import com.cba.mpos.aoer.repository.internal.TaskConfigRepository;
import com.cba.mpos.aoer.service.AppOperationService;
import com.cba.mpos.aoer.service.common.EmailService;
import com.cba.mpos.aoer.utils.AoerConstants;
import com.cba.mpos.aoer.utils.AoerUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AppOperationServiceImpl implements AppOperationService {

    @Autowired
    EmailService emailService;

    @Value("${sender.mail.address}")
    String senderMail;

    private final Logger LOGGER = LoggerFactory.getLogger(AppOperationServiceImpl.class);

    private final DeviceRepository deviceRepository;
    private final TranSummaryRepository tranSummaryRepository;
    private final MerchantRepository merchantRepository;
    private final TerminalRepository terminalRepository;
    private final EmailConfigRepository emailConfigRepository;
    private final TaskConfigRepository taskConfigRepository;
    private final PaperRollStatusRepository paperRollStatusRepository;
    private final FailedTransactionsRepository failedTransactionsRepository;

    public AppOperationServiceImpl(DeviceRepository deviceRepository, TranSummaryRepository tranSummaryRepository, MerchantRepository merchantRepository, TerminalRepository terminalRepository, EmailConfigRepository emailConfigRepository, TaskConfigRepository taskConfigRepository, PaperRollStatusRepository paperRollStatusRepository, FailedTransactionsRepository failedTransactionsRepository) {
        this.deviceRepository = deviceRepository;
        this.tranSummaryRepository = tranSummaryRepository;
        this.merchantRepository = merchantRepository;
        this.terminalRepository = terminalRepository;
        this.emailConfigRepository = emailConfigRepository;
        this.taskConfigRepository = taskConfigRepository;
        this.paperRollStatusRepository = paperRollStatusRepository;
        this.failedTransactionsRepository = failedTransactionsRepository;
    }

    @Override
    @Transactional
    public String notifyPaperRollStatus(String serialNo, String paperStatus) {
        LOGGER.info("Notifying Paper Roll Status.");
        Devices device = deviceRepository.findBySerialNoAndDeletedRec(serialNo, 0);
        if (device == null) {
            throw new NotFoundException("Device not found with serial no: " + serialNo);
        }
        LOGGER.debug("devices.getTerminals().size() = {}", device.getTerminalses().size());;
        Terminals terminal = device.getTerminalses().iterator().next();
        Merchants merchant = terminal.getMerchants();

        PaperRollStatus paperRollStatus = new PaperRollStatus();
        paperRollStatus.setMid(merchant.getMerchantId());
        paperRollStatus.setStatus(paperStatus);
        paperRollStatus.setSerialNo(serialNo);
        paperRollStatus.setDate(LocalDate.now());
        this.paperRollStatusRepository.saveAndFlush(paperRollStatus);

        EmailConfig emailConfig;
        MailDto mailDto = new MailDto();
        if (paperStatus.equals("OUT")) {
            emailConfig = emailConfigRepository.findByAction("OUT_OF_PAPER");
            mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
            mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
            mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
            mailDto.setSubject("(test) Paper Roll Alert");
            mailDto.setFrom(senderMail);

            Map<String, Object> properties = new HashMap<>();
            properties.put("merchantId", merchant.getMerchantId());
            properties.put("merchantName", merchant.getName());
            properties.put("merchantEmail", merchant.getEmail());

            mailDto.setProps(properties);
            mailDto.setTemplate("out_of_paper");
        } else {
            emailConfig = emailConfigRepository.findByAction("LOW_ON_PAPER");
            mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
            mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
            mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
            mailDto.setSubject("(test) Paper Roll Alert");
            mailDto.setFrom(senderMail);

            Map<String, Object> properties = new HashMap<>();
            properties.put("merchantId", merchant.getMerchantId());
            properties.put("merchantName", merchant.getName());
            properties.put("merchantEmail", merchant.getEmail());

            mailDto.setProps(properties);
            mailDto.setTemplate("low_on_paper");
        }
        emailService.sendHtmlEmail(mailDto);
        return "Notification has been sent.";
    }

    @Override
    public List<PaperRollStatus> getAllPaperRollStatus() {
        return this.paperRollStatusRepository.findAll();
    }

    @Override
    public Map<String, Object> getPaperStatusCountForPeriod(ReportReq reportReq) {
        Map<String, Object> result = new HashMap<>();
        result.put("low", paperRollStatusRepository.findByStatusAndDateBetween("LOW", reportReq.getFrom(),
                reportReq.getTo()));
        result.put("out", paperRollStatusRepository.findByStatusAndDateBetween("OUT", reportReq.getFrom(),
                reportReq.getTo()));
        return result;
    }

    @Override
    public String notifyOutOfGeoFence(DeviceDto deviceDto) {
        LOGGER.info("Notifying Out of Geo Fence.");
        EmailConfig emailConfig = emailConfigRepository.findByAction("OUT_OF_GEO_FENCE");
        MailDto mailDto = new MailDto();

        mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
        mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
        mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
        mailDto.setSubject("(test) POS Terminal Out of Geo Fence");
        mailDto.setFrom(senderMail);

        Map<String, Object> properties = new HashMap<>();
        properties.put("merchantId", deviceDto.getMid());
        properties.put("merchantName", deviceDto.getMerchantName());
        properties.put("merchantEmail", deviceDto.getEmail());
        properties.put("serialNo", deviceDto.getSerialNo());
        properties.put("terminalId", deviceDto.getTid());

        mailDto.setProps(properties);
        mailDto.setTemplate("geo_fence_alert");

        emailService.sendHtmlEmail(mailDto);
        return "Notification has been sent.";
    }

    @Override
    @Transactional
    public void checkUnsettledTerminalsAndNotify() throws Exception {
        LOGGER.info("Starting background task: checkUnsettledTerminalsAndNotify");
        TaskConfig taskConfig = taskConfigRepository.findByAction("UNSETTLED_ALERT");
        if (taskConfig.isEnabled()) {
            EmailConfig emailConfig = emailConfigRepository.findByAction("UNSETTLED_ALERT");
            List<TerminalDto> terminalDtoList = new ArrayList<>();
            terminalRepository.findByDeletedRec(0).forEach(terminal -> {
                if (terminal.getLastSettled() != null) {
                    LocalDateTime settledAt = terminal.getLastSettled().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    if (settledAt.isBefore(LocalDateTime.now().minusHours(taskConfig.getPeriod()))) {
                        TerminalDto terminalDto = new TerminalDto();
                        terminalDto.setTid(terminal.getTerminalId());
                        terminalDto.setMid(terminal.getMerchants().getMerchantId());
                        terminalDto.setMerchantName(terminal.getMerchants().getName());
                        terminalDto.setLastSettledOn(terminal.getLastSettled().toString());
                        terminalDtoList.add(terminalDto);
                    }
                }
            });

            if (!terminalDtoList.isEmpty()) {
                InputStream stream = this.getClass().getResourceAsStream("/reports/unsettled_terminals_alert.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(stream);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(terminalDtoList);
                Map<String, Object> parameters = new HashMap<>();
                Date now = new Date();
                parameters.put("date", now);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
                DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

                MailDto mailDto = new MailDto();
                mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
                mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
                mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
                mailDto.setSubject(String.format("(test) Unsettled Terminal Report as at: %s", now));
                mailDto.setFrom(senderMail);

                //TODO: html email?
                String fileName = String.format("Unsettled_Terminal_Report:%s.pdf", now);
                emailService.sendEmailWithAttachment(mailDto, fileName, dataSource);
            }
            LOGGER.info("Completed background task: checkUnsettledTerminalsAndNotify");
        } else {
            LOGGER.info("Background task: checkUnsettledTerminalsAndNotify is not enabled.");
        }
    }

    @Override
    @Transactional
    public void checkInactiveTerminalsAndNotify() throws Exception {
        LOGGER.info("Starting background task: checkInactiveTerminalsAndNotify");
        TaskConfig taskConfig = taskConfigRepository.findByAction("INACTIVE_ALERT");
        if (taskConfig.isEnabled()) {
            EmailConfig emailConfig = emailConfigRepository.findByAction("INACTIVE_ALERT");
            List<DeviceDto> deviceDtoList = new ArrayList<>();
            deviceRepository.findByDeletedRec(0).forEach(device -> {
                if (device.getLastActive() != null) {
                    LocalDateTime lastActive = device.getLastActive().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    if (lastActive.isBefore(LocalDateTime.now().minusHours(taskConfig.getPeriod()))) {
                        Terminals terminal = device.getTerminalses().iterator().next();
                        Merchants merchant = terminal.getMerchants();

                        DeviceDto deviceDto = new DeviceDto();
                        deviceDto.setMid(merchant.getMerchantId());
                        deviceDto.setMerchantName(merchant.getName());
                        deviceDto.setSerialNo(device.getSerialNo());
                        deviceDto.setLastActiveOn(device.getLastActive().toString());
                        deviceDtoList.add(deviceDto);
                    }
                }
            });
            if (!deviceDtoList.isEmpty()) {
                InputStream stream = this.getClass().getResourceAsStream("/reports/inactive_terminals_alert.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(stream);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(deviceDtoList);
                Map<String, Object> parameters = new HashMap<>();
                Date now = new Date();
                parameters.put("date", now);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
                DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

                MailDto mailDto = new MailDto();
                mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
                mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
                mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
                mailDto.setSubject(String.format("(test) Inactive Terminal Report as at: %s", now));
                mailDto.setFrom(senderMail);

                //TODO: html email?
                String fileName = String.format("Inactive_Terminal_Report:%s.pdf", now);
                emailService.sendEmailWithAttachment(mailDto, fileName, dataSource);
            }
            LOGGER.info("Completed background task: checkInactiveTerminalsAndNotify");
        } else {
            LOGGER.info("Background task: checkInactiveTerminalsAndNotify is not enabled.");
        }
    }

    @Override
    public void checkTranAmountAndNotify() throws Exception {
        LOGGER.info("Starting background task: checkTranAmountAndNotify");
        TaskConfig taskConfig = taskConfigRepository.findByAction("TRAN_AMOUNT");
        if (taskConfig.isEnabled()) {
            EmailConfig emailConfig = emailConfigRepository.findByAction("TRAN_AMOUNT");
            tranSummaryRepository.findByIsProcessedFalse().forEach(tranSummary -> {
                if (tranSummary.getAmount() >= taskConfig.getValue()) {
                    MailDto mailDto = new MailDto();
                    mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
                    mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
                    mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
                    mailDto.setSubject("(test) High Amount Transaction Notification");
                    mailDto.setFrom(senderMail);

                    Merchants merchant = merchantRepository.findByMerchantId(tranSummary.getMid());
                    Map<String, Object> properties = new HashMap<>();
                    properties.put("terminalId", tranSummary.getTid());
                    properties.put("merchantId", merchant.getMerchantId());
                    properties.put("merchantName", merchant.getName());
                    properties.put("merchantEmail", merchant.getEmail());
                    properties.put("amount", tranSummary.getAmount());

                    mailDto.setProps(properties);
                    mailDto.setTemplate("amount_alert");
                    emailService.sendHtmlEmail(mailDto);

                    tranSummary.setNotified(true);
                }
                tranSummary.setProcessed(true);
                tranSummaryRepository.saveAndFlush(tranSummary);
            });
            LOGGER.info("Completed background task: checkTranAmountAndNotify");
        } else {
            LOGGER.info("Background task: checkTranAmountAndNotify is not enabled");
        }
    }

    @Transactional
    @Override
    public void checkTranVelocityAndNotify() throws Exception {
        LOGGER.info("Starting background task: checkTranVelocityAndNotify");
        TaskConfig taskConfig = taskConfigRepository.findByAction("TRAN_VELOCITY");
        if (taskConfig.isEnabled()) {
            EmailConfig emailConfig = emailConfigRepository.findByAction("TRAN_VELOCITY");
            LocalDateTime to = LocalDateTime.now();
            LocalDateTime from = to.minusHours(taskConfig.getPeriod());

            List<TerminalDto> terminalDtoList = new ArrayList<>();
            terminalRepository.findByDeletedRec(0).forEach(terminal -> {
                long tranCount = tranSummaryRepository.countByTidAndDateTimeBetween(terminal.getTerminalId(), from, to);
                if (tranCount >= taskConfig.getValue()) {
                    TerminalDto terminalDto = new TerminalDto();
                    terminalDto.setTid(terminal.getTerminalId());
                    terminalDto.setMid(terminal.getMerchants().getMerchantId());
                    terminalDto.setMerchantName(terminal.getMerchants().getName());
                    terminalDto.setTranCount(tranCount);
                    terminalDtoList.add(terminalDto);
                }
            });

            if (!terminalDtoList.isEmpty()) {
                InputStream stream = this.getClass().getResourceAsStream("/reports/transaction_velocity_alert.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(stream);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(terminalDtoList);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("date", to);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
                DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

                MailDto mailDto = new MailDto();
                mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
                mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
                mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
                mailDto.setSubject(String.format("(test) Transaction Velocity Report as at: %s", to));
                mailDto.setFrom(senderMail);

                //TODO: html email?
                String fileName = String.format("Transaction_Velocity_Report:%s.pdf", to);
                emailService.sendEmailWithAttachment(mailDto, fileName, dataSource);
            }
            LOGGER.info("Completed background task: checkTranVelocityAndNotify");
        } else {
            LOGGER.info("Background task: checkTranVelocityAndNotify is not enabled.");
        }
    }

    @Transactional
    @Override
    public void checkFailedTranAndNotify() throws Exception {
        LOGGER.info("Starting background task: checkFailedTranAndNotify");
        TaskConfig taskConfig = taskConfigRepository.findByAction("FAILED_FREQUENCY");
        if (taskConfig.isEnabled()) {
            EmailConfig emailConfig = emailConfigRepository.findByAction("FAILED_FREQUENCY");
            LocalDateTime to = LocalDateTime.now();
            LocalDateTime from = to.minusHours(taskConfig.getPeriod());

            List<TerminalDto> terminalDtoList = new ArrayList<>();
            terminalRepository.findByDeletedRec(0).forEach(terminal -> {
                long failedTranCount = failedTransactionsRepository.countByTidAndRespCodeNotEqualAndDateTimeBetween(terminal.getTerminalId(),
                        AoerConstants.RESP_CODE_TIME_OUT, from, to);
                if (failedTranCount >= taskConfig.getValue()) {
                    TerminalDto terminalDto = new TerminalDto();
                    terminalDto.setTid(terminal.getTerminalId());
                    terminalDto.setMid(terminal.getMerchants().getMerchantId());
                    terminalDto.setMerchantName(terminal.getMerchants().getName());
                    terminalDto.setFailedTranCount(failedTranCount);
                    terminalDtoList.add(terminalDto);
                }
            });

            if (!terminalDtoList.isEmpty()) {
                InputStream stream = this.getClass().getResourceAsStream("/reports/failed_tran_frequency_alert.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(stream);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(terminalDtoList);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("date", to);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
                DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

                MailDto mailDto = new MailDto();
                mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
                mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
                mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
                mailDto.setSubject(String.format("(test) Declined Transaction Report as at: %s", to));
                mailDto.setFrom(senderMail);

                //TODO: html email?
                String fileName = String.format("Declined_Transaction_Report:%s.pdf", to);
                emailService.sendEmailWithAttachment(mailDto, fileName, dataSource);
            }
            LOGGER.info("Completed background task: checkFailedTranAndNotify");
        } else {
            LOGGER.info("Background task: checkFailedTranAndNotify is not enabled.");
        }
    }

    @Transactional
    @Override
    public void checkTimeoutTranAndNotify() throws Exception {
        LOGGER.info("Starting background task: checkTimeoutTranAndNotify");
        TaskConfig taskConfig = taskConfigRepository.findByAction("TIMEOUT_FREQUENCY");
        if (taskConfig.isEnabled()) {
            EmailConfig emailConfig = emailConfigRepository.findByAction("TIMEOUT_FREQUENCY");
            LocalDateTime to = LocalDateTime.now();
            LocalDateTime from = to.minusHours(taskConfig.getPeriod());

            List<TerminalDto> terminalDtoList = new ArrayList<>();
            terminalRepository.findByDeletedRec(0).forEach(terminal -> {
                long timeoutCount = failedTransactionsRepository.countByTidAndRespCodeAndDateTimeBetween(terminal.getTerminalId(),
                        AoerConstants.RESP_CODE_TIME_OUT, from, to);
                if (timeoutCount >= taskConfig.getValue()) {
                    TerminalDto terminalDto = new TerminalDto();
                    terminalDto.setTid(terminal.getTerminalId());
                    terminalDto.setMid(terminal.getMerchants().getMerchantId());
                    terminalDto.setMerchantName(terminal.getMerchants().getName());
                    terminalDto.setTimeoutCount(timeoutCount);
                    terminalDtoList.add(terminalDto);
                }
            });

            if (!terminalDtoList.isEmpty()) {
                InputStream stream = this.getClass().getResourceAsStream("/reports/timeout_frequency_alert.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(stream);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(terminalDtoList);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("date", to);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
                DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

                MailDto mailDto = new MailDto();
                mailDto.setTo(AoerUtils.commaSeparatedStringToArray(emailConfig.getTo()));
                mailDto.setCc(AoerUtils.commaSeparatedStringToArray(emailConfig.getCc()));
                mailDto.setBcc(AoerUtils.commaSeparatedStringToArray(emailConfig.getBcc()));
                mailDto.setSubject(String.format("(test) Transaction Timeout Report as at: %s", to));
                mailDto.setFrom(senderMail);

                //TODO: html email?
                String fileName = String.format("Transaction_Timeout_Report:%s.pdf", to);
                emailService.sendEmailWithAttachment(mailDto, fileName, dataSource);
            }
            LOGGER.info("Completed background task: checkTimeoutTranAndNotify");
        } else {
            LOGGER.info("Background task: checkTimeoutTranAndNotify is not enabled.");
        }
    }

    @Override
    public Map<String, Integer> getDeviceStatus() throws Exception {
        Map<String, Integer> result = new HashMap<>();
        int activeCount = 0;
        int inactiveCount = 0;
        TaskConfig taskConfig = taskConfigRepository.findByAction("INACTIVE_ALERT");

        List<Devices> deviceList = deviceRepository.findByDeletedRec(0);
        for (Devices device : deviceList) {
            if (device.getLastActive() != null) {
                LocalDateTime lastActive = device.getLastActive().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                if (lastActive.isAfter(LocalDateTime.now().minusHours(taskConfig.getPeriod()))) {
                    activeCount++;
                } else {
                    inactiveCount++;
                }
            } else {
                inactiveCount++;
            }
        }
        result.put("activeCount", activeCount);
        result.put("inactiveCount", inactiveCount);
        return result;
    }

    @Override
    public Map<String, Object> getTranStatusForPeriod(ReportReq reportReq) throws Exception {
        List<TransactionDto> highAmount = new ArrayList<>();
        List<TransactionDto> unsettled = new ArrayList<>();
        List<TransactionDto> declined = new ArrayList<>();
        List<TransactionDto> timeout = new ArrayList<>();

        List<TranSummary> tranSummaryList = tranSummaryRepository.getAllForPeriod(reportReq.getFrom(), reportReq.getTo());
        for (TranSummary tranSummary : tranSummaryList) {
            if (tranSummary.isNotified()) {
                highAmount.add(convertTranSummaryToTranDto(tranSummary));
            }
            if (!tranSummary.getIsSettled()) {
                unsettled.add(convertTranSummaryToTranDto(tranSummary));
            }
        }

        List<FailedTransactions> failedTransactionsList = failedTransactionsRepository.getAllForPeriod(reportReq.getFrom(),
                reportReq.getTo());
        for (FailedTransactions failedTransaction : failedTransactionsList) {
            if (failedTransaction.getRespCode().equalsIgnoreCase(AoerConstants.RESP_CODE_TIME_OUT)) {
                timeout.add(convertFailedTranToTranDto(failedTransaction));
            } else {
                declined.add(convertFailedTranToTranDto(failedTransaction));
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("highAmount", highAmount);
        result.put("unsettled", unsettled);
        result.put("declined", declined);
        result.put("timeout", timeout);
        return result;
    }

    private TransactionDto convertTranSummaryToTranDto(TranSummary tranSummary) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(tranSummary.getId());
        transactionDto.setTid(tranSummary.getTid());
        transactionDto.setMid(tranSummary.getMid());
        transactionDto.setAmount(new BigDecimal(tranSummary.getAmount()).divide(new BigDecimal(100), 2,
                RoundingMode.HALF_UP));
        transactionDto.setTimestamp(tranSummary.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss")));
        transactionDto.setPaymentMode(tranSummary.getPaymentMode());
        transactionDto.setCustMobile(tranSummary.getCustMobile());
        transactionDto.setTransType(tranSummary.getTransType());
        transactionDto.setCardLabel(tranSummary.getCardLabel());
        transactionDto.setCurrency(tranSummary.getCurrency());
        transactionDto.setPan(tranSummary.getPan());
        transactionDto.setSignData(tranSummary.getSignData());
        transactionDto.setEntryMode(tranSummary.getEntryMode());
        transactionDto.setIsSettled(tranSummary.getIsSettled());
        transactionDto.setIsAway(tranSummary.getIsAway());
        return transactionDto;
    }

    private TransactionDto convertFailedTranToTranDto(FailedTransactions failedTran) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(failedTran.getId());
        transactionDto.setTid(failedTran.getTerminals().getTerminalId());
        transactionDto.setMid(failedTran.getMerchants().getMerchantId());
        transactionDto.setAmount(new BigDecimal(failedTran.getAmount()).divide(new BigDecimal(100), 2,
                RoundingMode.HALF_UP));
        transactionDto.setTimestamp(failedTran.getDateTime().toString());
        transactionDto.setPaymentMode(failedTran.getPaymentMode());
        transactionDto.setCustMobile(failedTran.getCustMobile());
        transactionDto.setTransType(failedTran.getTransType());
        transactionDto.setCardLabel(failedTran.getCardLabel());
        transactionDto.setCurrency(failedTran.getCurrency());
        transactionDto.setPan(failedTran.getPan());
        transactionDto.setSignData(failedTran.getSignData());
        transactionDto.setEntryMode(failedTran.getEntryMode());
        return transactionDto;
    }
}
