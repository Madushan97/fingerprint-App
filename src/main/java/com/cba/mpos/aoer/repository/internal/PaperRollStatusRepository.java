package com.cba.mpos.aoer.repository.internal;

import com.cba.mpos.aoer.model.internal.PaperRollStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaperRollStatusRepository extends JpaRepository<PaperRollStatus, Integer> {

    long countByStatusAndDateBetween(String status, LocalDate startDate, LocalDate endDate);

    List<PaperRollStatus> findByStatusAndDateBetween(String status, LocalDate startDate, LocalDate endDate);

}
