package com.cba.mpos.aoer.model.external;
// Generated Feb 15, 2023 9:07:45 AM by Hibernate Tools 3.6.0


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Permissions generated by hbm2java
 */
@Entity
@Table(name="Permissions")
public class Permissions  implements java.io.Serializable {


     private Integer id;
     private Resources resources;
     private Roles roles;
     private Byte createD;
     private Byte readD;
     private Byte updateD;
     private Byte deleteD;
     private Byte createdBy;
     private Byte modifiedBy;
     private Byte deletedBy;
     private Integer deletedRec;
     private Date createdAt;
     private Date updatedAt;

    public Permissions() {
    }

	
    public Permissions(Date createdAt, Date updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Permissions(Resources resources, Roles roles, Byte createD, Byte readD, Byte updateD, Byte deleteD, Byte createdBy, Byte modifiedBy, Byte deletedBy, Integer deletedRec, Date createdAt, Date updatedAt) {
       this.resources = resources;
       this.roles = roles;
       this.createD = createD;
       this.readD = readD;
       this.updateD = updateD;
       this.deleteD = deleteD;
       this.createdBy = createdBy;
       this.modifiedBy = modifiedBy;
       this.deletedBy = deletedBy;
       this.deletedRec = deletedRec;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="resourceId")
    public Resources getResources() {
        return this.resources;
    }
    
    public void setResources(Resources resources) {
        this.resources = resources;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="roleId")
    public Roles getRoles() {
        return this.roles;
    }
    
    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    
    @Column(name="createD")
    public Byte getCreateD() {
        return this.createD;
    }
    
    public void setCreateD(Byte createD) {
        this.createD = createD;
    }

    
    @Column(name="readD")
    public Byte getReadD() {
        return this.readD;
    }
    
    public void setReadD(Byte readD) {
        this.readD = readD;
    }

    
    @Column(name="updateD")
    public Byte getUpdateD() {
        return this.updateD;
    }
    
    public void setUpdateD(Byte updateD) {
        this.updateD = updateD;
    }

    
    @Column(name="deleteD")
    public Byte getDeleteD() {
        return this.deleteD;
    }
    
    public void setDeleteD(Byte deleteD) {
        this.deleteD = deleteD;
    }

    
    @Column(name="createdBy")
    public Byte getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(Byte createdBy) {
        this.createdBy = createdBy;
    }

    
    @Column(name="modifiedBy")
    public Byte getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(Byte modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    
    @Column(name="deletedBy")
    public Byte getDeletedBy() {
        return this.deletedBy;
    }
    
    public void setDeletedBy(Byte deletedBy) {
        this.deletedBy = deletedBy;
    }

    
    @Column(name="deletedRec")
    public Integer getDeletedRec() {
        return this.deletedRec;
    }
    
    public void setDeletedRec(Integer deletedRec) {
        this.deletedRec = deletedRec;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdAt", nullable=false, length=19)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updatedAt", nullable=false, length=19)
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }




}

