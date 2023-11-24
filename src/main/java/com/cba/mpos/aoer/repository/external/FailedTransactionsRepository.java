package com.cba.mpos.aoer.repository.external;

import com.cba.mpos.aoer.model.external.FailedTransactions;
import com.cba.mpos.aoer.repository.common.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FailedTransactionsRepository extends ReadOnlyRepository<FailedTransactions, Integer> {

    @Query("SELECT COUNT(ft.id) FROM FailedTransactions ft WHERE ft.terminals.terminalId = :tid AND DATE(ft.dateTime) >= DATE(:startTime) " +
            "AND DATE(ft.dateTime) <= DATE(:endTime)")
    long countByTidAndDateTimeBetween(@Param("tid") String tid, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT COUNT(ft.id) FROM FailedTransactions ft WHERE ft.terminals.terminalId = :tid AND ft.respCode = :respCode " +
            "AND DATE(ft.dateTime) >= DATE(:startTime) AND DATE(ft.dateTime) <= DATE(:endTime)")
    long countByTidAndRespCodeAndDateTimeBetween(@Param("tid") String tid, @Param("respCode") String respCode,
                                                 @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT COUNT(ft.id) FROM FailedTransactions ft WHERE ft.terminals.terminalId = :tid AND ft.respCode <> :respCode " +
            "AND DATE(ft.dateTime) >= DATE(:startTime) AND DATE(ft.dateTime) <= DATE(:endTime)")
    long countByTidAndRespCodeNotEqualAndDateTimeBetween(@Param("tid") String tid, @Param("respCode") String respCode,
                                                 @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT ft FROM FailedTransactions ft WHERE DATE(ft.dateTime) >= DATE(:from) AND DATE(ft.dateTime) <= DATE(:to)")
    List<FailedTransactions> getAllForPeriod(@Param("from") LocalDate from, @Param("to") LocalDate to);

}
