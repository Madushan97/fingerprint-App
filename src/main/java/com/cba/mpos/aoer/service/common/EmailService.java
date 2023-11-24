package com.cba.mpos.aoer.service.common;

import com.cba.mpos.aoer.dto.MailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Async
    public void sendHtmlEmail(MailDto mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(mail.getProps());

            String html = templateEngine.process(mail.getTemplate(), context);
            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            helper.setBcc(mail.getBcc());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            emailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("Error occurred while sending email", e);
        }
    }

    @Async
    public void sendEmailWithAttachment(MailDto mail, String fileName, DataSource dataSource) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(mail.getProps());

            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            helper.setBcc(mail.getBcc());
            helper.setText("Please find the attached report.");
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            helper.addAttachment(fileName, dataSource);
            emailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("Error occurred while sending email", e);
        }
    }
}
