package com.cba.mpos.aoer.repository.internal;

import com.cba.mpos.aoer.model.internal.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfigRepository extends JpaRepository<EmailConfig, Integer> {

    EmailConfig findByAction(String action);
}
