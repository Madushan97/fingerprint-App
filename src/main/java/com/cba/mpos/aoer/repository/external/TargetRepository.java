package com.cba.mpos.aoer.repository.external;

import com.cba.mpos.aoer.model.external.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<TargetEntity, Long> {
}
