//package com.cba.mpos.aoer.model.external;
//// Generated Feb 15, 2023 9:07:45 AM by Hibernate Tools 3.6.0
//
//
//import javax.persistence.*;
//import java.util.Date;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
///**
// * UserRoles generated by hbm2java
// */
//@Entity
//@Table(name="UserRoles", uniqueConstraints = @UniqueConstraint(columnNames={"userId", "roleId"}))
//public class UserRoles  implements java.io.Serializable {
//
//
//     private Integer id;
//     private User user;
//     private Roles roles;
//     private Integer deletedRec;
//     private Integer createdBy;
//     private Integer modifiedBy;
//     private Integer deletedBy;
//     private Date createdAt;
//     private Date updatedAt;
//
//    public UserRoles() {
//    }
//
//
//    public UserRoles(Date createdAt, Date updatedAt) {
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//    public UserRoles(User user, Roles roles, Integer deletedRec, Integer createdBy, Integer modifiedBy, Integer deletedBy, Date createdAt, Date updatedAt) {
//       this.user = user;
//       this.roles = roles;
//       this.deletedRec = deletedRec;
//       this.createdBy = createdBy;
//       this.modifiedBy = modifiedBy;
//       this.deletedBy = deletedBy;
//       this.createdAt = createdAt;
//       this.updatedAt = updatedAt;
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
//    @JoinColumn(name="userId")
//    public User getUser() {
//        return this.user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//@ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="roleId")
//    public Roles getRoles() {
//        return this.roles;
//    }
//
//    public void setRoles(Roles roles) {
//        this.roles = roles;
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
//
//
//
//}
//
//
