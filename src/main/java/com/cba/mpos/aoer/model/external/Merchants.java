//package com.cba.mpos.aoer.model.external;
//// Generated Jul 31, 2023 7:18:35 PM by Hibernate Tools 3.6.0
//
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
///**
// * Merchants generated by hbm2java
// */
//@Entity
//@Table(name="Merchants", uniqueConstraints = @UniqueConstraint(columnNames={"merchantId", "email", "deletedRec"}))
//public class Merchants  implements java.io.Serializable {
//
//     private Integer id;
//     private Partners partners;
//     private String name;
//     private String merchantId;
//     private String email;
//     private String contactNo;
//     private String province;
//     private String district;
//     private String mcc;
//     private Integer deletedRec;
//     private Integer createdBy;
//     private Integer modifiedBy;
//     private Integer deletedBy;
//     private Float lat;
//     private Float lng;
//     private Integer radius;
//     private Date createdAt;
//     private Date updatedAt;
//     private Set<MerchantWls> merchantWlses = new HashSet<MerchantWls>(0);
//     private Set<Terminals> terminalses = new HashSet<Terminals>(0);
//     private Set<User> users = new HashSet<User>(0);
//     private Set<Transactions> transactionses = new HashSet<Transactions>(0);
//     private Set<FailedTransactions> failedTransactionses = new HashSet<FailedTransactions>(0);
//
//    public Merchants() {
//    }
//
//
//    public Merchants(String name, String merchantId, String email, String province, String district, Date createdAt, Date updatedAt) {
//        this.name = name;
//        this.merchantId = merchantId;
//        this.email = email;
//        this.province = province;
//        this.district = district;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//    public Merchants(Partners partners, String name, String merchantId, String email, String contactNo, String province, String district, String mcc, Integer deletedRec, Integer createdBy, Integer modifiedBy, Integer deletedBy, Float lat, Float lng, Integer radius, Date createdAt, Date updatedAt, Set<MerchantWls> merchantWlses, Set<Terminals> terminalses, Set<User> users, Set<Transactions> transactionses, Set<FailedTransactions> failedTransactionses) {
//       this.partners = partners;
//       this.name = name;
//       this.merchantId = merchantId;
//       this.email = email;
//       this.contactNo = contactNo;
//       this.province = province;
//       this.district = district;
//       this.mcc = mcc;
//       this.deletedRec = deletedRec;
//       this.createdBy = createdBy;
//       this.modifiedBy = modifiedBy;
//       this.deletedBy = deletedBy;
//       this.lat = lat;
//       this.lng = lng;
//       this.radius = radius;
//       this.createdAt = createdAt;
//       this.updatedAt = updatedAt;
//       this.merchantWlses = merchantWlses;
//       this.terminalses = terminalses;
//       this.users = users;
//       this.transactionses = transactionses;
//       this.failedTransactionses = failedTransactionses;
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
//@ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="partnerId")
//    public Partners getPartners() {
//        return this.partners;
//    }
//
//    public void setPartners(Partners partners) {
//        this.partners = partners;
//    }
//
//
//    @Column(name="name", nullable=false, length=50)
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    @Column(name="merchantId", unique=true, nullable=false, length=16)
//    public String getMerchantId() {
//        return this.merchantId;
//    }
//
//    public void setMerchantId(String merchantId) {
//        this.merchantId = merchantId;
//    }
//
//
//    @Column(name="email", nullable=false, length=40)
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
//    @Column(name="contactNo", length=15)
//    public String getContactNo() {
//        return this.contactNo;
//    }
//
//    public void setContactNo(String contactNo) {
//        this.contactNo = contactNo;
//    }
//
//
//    @Column(name="province", nullable=false, length=30)
//    public String getProvince() {
//        return this.province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }
//
//
//    @Column(name="district", nullable=false, length=30)
//    public String getDistrict() {
//        return this.district;
//    }
//
//    public void setDistrict(String district) {
//        this.district = district;
//    }
//
//
//    @Column(name="mcc", length=6)
//    public String getMcc() {
//        return this.mcc;
//    }
//
//    public void setMcc(String mcc) {
//        this.mcc = mcc;
//    }
//
//
//    @Column(name="deletedRec")
//    public Integer getDeletedRec() {
//        return this.deletedRec;
//    }
//
//    public void setDeletedRec(Integer deletedRec) {
//        this.deletedRec = deletedRec;
//    }
//
//
//    @Column(name="createdBy")
//    public Integer getCreatedBy() {
//        return this.createdBy;
//    }
//
//    public void setCreatedBy(Integer createdBy) {
//        this.createdBy = createdBy;
//    }
//
//
//    @Column(name="modifiedBy")
//    public Integer getModifiedBy() {
//        return this.modifiedBy;
//    }
//
//    public void setModifiedBy(Integer modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//
//    @Column(name="deletedBy")
//    public Integer getDeletedBy() {
//        return this.deletedBy;
//    }
//
//    public void setDeletedBy(Integer deletedBy) {
//        this.deletedBy = deletedBy;
//    }
//
//
//    @Column(name="lat", precision=12, scale=0)
//    public Float getLat() {
//        return this.lat;
//    }
//
//    public void setLat(Float lat) {
//        this.lat = lat;
//    }
//
//
//    @Column(name="lng", precision=12, scale=0)
//    public Float getLng() {
//        return this.lng;
//    }
//
//    public void setLng(Float lng) {
//        this.lng = lng;
//    }
//
//
//    @Column(name="radius")
//    public Integer getRadius() {
//        return this.radius;
//    }
//
//    public void setRadius(Integer radius) {
//        this.radius = radius;
//    }
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="createdAt", nullable=false, length=19)
//    public Date getCreatedAt() {
//        return this.createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="updatedAt", nullable=false, length=19)
//    public Date getUpdatedAt() {
//        return this.updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//@OneToMany(fetch=FetchType.LAZY, mappedBy="merchants")
//    public Set<MerchantWls> getMerchantWlses() {
//        return this.merchantWlses;
//    }
//
//    public void setMerchantWlses(Set<MerchantWls> merchantWlses) {
//        this.merchantWlses = merchantWlses;
//    }
//
//@OneToMany(fetch=FetchType.LAZY, mappedBy="merchants")
//    public Set<Terminals> getTerminalses() {
//        return this.terminalses;
//    }
//
//    public void setTerminalses(Set<Terminals> terminalses) {
//        this.terminalses = terminalses;
//    }
//
//@OneToMany(fetch=FetchType.LAZY, mappedBy="merchants")
//    public Set<User> getUsers() {
//        return this.users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//@OneToMany(fetch=FetchType.LAZY, mappedBy="merchants")
//    public Set<Transactions> getTransactionses() {
//        return this.transactionses;
//    }
//
//    public void setTransactionses(Set<Transactions> transactionses) {
//        this.transactionses = transactionses;
//    }
//
//@OneToMany(fetch=FetchType.LAZY, mappedBy="merchants")
//    public Set<FailedTransactions> getFailedTransactionses() {
//        return this.failedTransactionses;
//    }
//
//    public void setFailedTransactionses(Set<FailedTransactions> failedTransactionses) {
//        this.failedTransactionses = failedTransactionses;
//    }
//
//}
//
//
