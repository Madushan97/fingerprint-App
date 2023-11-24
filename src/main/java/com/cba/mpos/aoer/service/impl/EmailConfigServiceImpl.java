package com.cba.mpos.aoer.service.impl;

import com.cba.mpos.aoer.exception.NotFoundException;
import com.cba.mpos.aoer.model.internal.EmailConfig;
import com.cba.mpos.aoer.repository.internal.EmailConfigRepository;
import com.cba.mpos.aoer.service.EmailConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailConfigServiceImpl implements EmailConfigService {

    private final EmailConfigRepository emailConfigRepository;
    
    private final Logger LOGGER = LoggerFactory.getLogger(EmailConfigServiceImpl.class);

    public EmailConfigServiceImpl(EmailConfigRepository emailConfigRepository) {
        this.emailConfigRepository = emailConfigRepository;
    }

    @Override
    public List<EmailConfig> findAll() throws Exception {
        return emailConfigRepository.findAll();
    }

    @Override
    public EmailConfig findById(Integer id) throws Exception {
        return emailConfigRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found for ID: " + id));
    }

    @Override
    public EmailConfig create(EmailConfig emailConfig) throws Exception {
        return emailConfigRepository.saveAndFlush(emailConfig);
    }

    @Override
    public EmailConfig update(Integer id, EmailConfig emailConfig) throws Exception {
        EmailConfig existing = this.findById(id);
        emailConfig.setId(existing.getId());
        return emailConfigRepository.saveAndFlush(emailConfig);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        this.findById(id);
        emailConfigRepository.deleteById(id);
    }
}
