//package com.cba.mpos.aoer.repository.external;
//
//import com.cba.mpos.aoer.model.external.TranSummary;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface TranSummaryRepository extends JpaRepository<TranSummary, Integer> {
//
//    List<TranSummary> findByIsProcessedFalse();
//
//    long countByTidAndDateTimeBetween(String tid, LocalDateTime startTime, LocalDateTime endTime);
//
//    @Query("SELECT COUNT(ts.id) FROM TranSummary ts WHERE ts.tid = :tid AND DATE(ts.dateTime) >= DATE(:from) AND DATE(ts.dateTime) <= DATE(:to)")
//    long getCountByTidForPeriod(@Param("tid") String tid, @Param("from") LocalDate from, @Param("to") LocalDate to);
//
//    @Query("SELECT SUM(amount) FROM TranSummary ts WHERE ts.tid = :tid AND ts.transType = :tranType AND DATE(ts.dateTime) >= DATE(:from) " +
//            "AND DATE(ts.dateTime) <= DATE(:to)")
//    Integer getTotalAmountByTidAndTranTypeForPeriod(@Param("tid") String tid, @Param("tranType") String tranType, @Param("from") LocalDate from,
//                                                    @Param("to") LocalDate to);
//
//    @Query("SELECT ts FROM TranSummary ts WHERE ts.amount > :limit AND ts.transType = :tranType AND DATE(ts.dateTime) >= DATE(:from) " +
//            "AND DATE(ts.dateTime) <= DATE(:to)")
//    List<TranSummary> getAllByTranTypeAndAmountGrtThanForPeriod(@Param("tranType") String tranType, @Param("limit") int limit,
//                                                                @Param("from") LocalDate from, @Param("to") LocalDate to);
//
//    @Query("SELECT ts FROM TranSummary ts WHERE DATE(ts.dateTime) >= DATE(:from) AND DATE(ts.dateTime) <= DATE(:to)")
//    List<TranSummary> getAllForPeriod(@Param("from") LocalDate from, @Param("to") LocalDate to);
//}
