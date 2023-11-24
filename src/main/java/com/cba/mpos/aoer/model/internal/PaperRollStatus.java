//package com.cba.mpos.aoer.model.internal;
//
//import com.cba.mpos.aoer.model.common.AuditEntity;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.time.LocalDate;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "paper_roll_status")
//@DynamicInsert
//@DynamicUpdate
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//public class PaperRollStatus extends AuditEntity {
//
//    @Column(columnDefinition = "VARCHAR(16)")
//    private String mid;
//
//    @Column(columnDefinition = "VARCHAR(150)")
//    private String serialNo;
//
//    private LocalDate date;
//
//    @Column(columnDefinition = "VARCHAR(10)")
//    private String status;
//
//}
