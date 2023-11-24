package com.cba.mpos.aoer.service;

import com.cba.mpos.aoer.model.internal.EmailConfig;

import java.util.List;

public interface EmailConfigService {

    List<EmailConfig> findAll() throws Exception;

    EmailConfig findById(Integer id) throws Exception;

    EmailConfig create(EmailConfig emailConfig) throws Exception;

    EmailConfig update(Integer id, EmailConfig emailConfig) throws Exception;

    void deleteById(Integer id) throws Exception;
}
