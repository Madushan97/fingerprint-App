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
//@Table(name = "email_config")
//@DynamicInsert
//@DynamicUpdate
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//public class EmailConfig extends AuditEntity {
//
//    @Column(name = "action", columnDefinition = "VARCHAR(30)", nullable = false, unique = true, updatable = false)
//    private String action;
//
//    @Column(name = "to_list", columnDefinition = "VARCHAR(500) default ''")
//    private String to;
//
//    @Column(name = "cc", columnDefinition = "VARCHAR(500) default ''")
//    private String cc;
//
//    @Column(name = "bcc", columnDefinition = "VARCHAR(500) default ''")
//    private String bcc;
//
//}
