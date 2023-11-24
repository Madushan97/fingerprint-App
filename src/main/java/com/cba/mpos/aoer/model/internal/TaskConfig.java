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
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "task_config")
//@DynamicInsert
//@DynamicUpdate
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//public class TaskConfig extends AuditEntity {
//
//    @Column(name = "action", columnDefinition = "VARCHAR(30)", nullable = false, unique = true, updatable = false)
//    private String action;
//
//    private int value;
//    private int period;
//
//    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean isEnabled;
//
//    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
//    private String cron;
//}
