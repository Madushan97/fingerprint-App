//package com.cba.mpos.aoer.model.external;
//// Generated Feb 15, 2023 9:07:45 AM by Hibernate Tools 3.6.0
//
//
//import javax.persistence.*;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
///**
// * ApplicationSignatures generated by hbm2java
// */
//@Entity
//@Table(name="ApplicationSignatures")
//public class ApplicationSignatures  implements java.io.Serializable {
//
//
//     private Integer id;
//     private String appVersion;
//     private String appSignature;
//     private String packageFileSignature;
//     private Integer sysFileSize;
//
//    public ApplicationSignatures() {
//    }
//
//
//    public ApplicationSignatures(String appVersion, String appSignature, String packageFileSignature) {
//        this.appVersion = appVersion;
//        this.appSignature = appSignature;
//        this.packageFileSignature = packageFileSignature;
//    }
//    public ApplicationSignatures(String appVersion, String appSignature, String packageFileSignature, Integer sysFileSize) {
//       this.appVersion = appVersion;
//       this.appSignature = appSignature;
//       this.packageFileSignature = packageFileSignature;
//       this.sysFileSize = sysFileSize;
//    }
//
//     @Id @GeneratedValue(strategy=IDENTITY)
//
//
//    @Column(name="id", unique=true, nullable=false)
//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//
//    @Column(name="appVersion", nullable=false, length=10)
//    public String getAppVersion() {
//        return this.appVersion;
//    }
//
//    public void setAppVersion(String appVersion) {
//        this.appVersion = appVersion;
//    }
//
//
//    @Column(name="appSignature", nullable=false, length=65535)
//    public String getAppSignature() {
//        return this.appSignature;
//    }
//
//    public void setAppSignature(String appSignature) {
//        this.appSignature = appSignature;
//    }
//
//
//    @Column(name="packageFileSignature", nullable=false, length=65535)
//    public String getPackageFileSignature() {
//        return this.packageFileSignature;
//    }
//
//    public void setPackageFileSignature(String packageFileSignature) {
//        this.packageFileSignature = packageFileSignature;
//    }
//
//
//    @Column(name="sysFileSize")
//    public Integer getSysFileSize() {
//        return this.sysFileSize;
//    }
//
//    public void setSysFileSize(Integer sysFileSize) {
//        this.sysFileSize = sysFileSize;
//    }
//
//
//
//
//}
//
//
