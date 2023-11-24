//package com.cba.mpos.aoer.model.external;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "TranSummary")
//@DynamicInsert
//@DynamicUpdate
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//public class TranSummary {
//
//    @Id
//    private Integer id;
//
//    @Column(columnDefinition = "VARCHAR(65)", nullable = false)
//    private String originId;
//
//    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
//    private String paymentMode;
//
//    @Column(columnDefinition = "VARCHAR(12)")
//    private String custMobile;
//
//    @Column(columnDefinition = "VARCHAR(20)")
//    private String transType;
//
//    @Column(columnDefinition = "VARCHAR(20)")
//    private String cardLabel;
//
//    private Integer traceNo;
//
//    private Integer invoiceNo;
//
//    private int amount;
//
//    @Column(columnDefinition = "VARCHAR(6)", nullable = false)
//    private String currency;
//
//    private Integer batchNo;
//
//    @Column(columnDefinition = "VARCHAR(25)")
//    private String pan;
//
//    @Column(columnDefinition = "VARCHAR(16)")
//    private String mid;
//
//    @Column(columnDefinition = "VARCHAR(9)")
//    private String tid;
//
//    private LocalDateTime dateTime;
//
//    @Column(columnDefinition = "VARCHAR(5)")
//    private String expDate;
//
//    @Column(columnDefinition = "VARCHAR(5)")
//    private String nii;
//
//    @Column(columnDefinition = "VARCHAR(20)")
//    private String rrn;
//
//    @Column(columnDefinition = "VARCHAR(20)")
//    private String authCode;
//
//    @Column(columnDefinition = "VARCHAR(10000)")
//    private String signData;
//
//    private Integer tipAmount;
//
//    private String entryMode;
//
//    private String dccCurrency;
//
//    private Integer dccTranAmount;
//
//    private Boolean isSettled;
//
//    private Integer settledMethod;
//
//    @Column(precision=12, scale=0)
//    private Float lat;
//
//    @Column(precision=12, scale=0)
//    private Float lng;
//
//    private Boolean isAway;
//
//    private LocalDateTime createdAt;
//
//    @Column(columnDefinition = "DATETIME  DEFAULT CURRENT_TIMESTAMP")
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//
//    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean isProcessed;
//
//    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean isNotified;
//}
