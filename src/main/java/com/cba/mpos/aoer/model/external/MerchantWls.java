package com.cba.mpos.aoer.model.external;
// Generated Feb 15, 2023 9:07:45 AM by Hibernate Tools 3.6.0


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * MerchantWls generated by hbm2java
 */
@Entity
@Table(name="MerchantWLs")
public class MerchantWls  implements java.io.Serializable {


     private Integer id;
     private Merchants merchants;
     private String merchantId;
     private String referenceNo;
     private String onboardingStatus;
     private String wlInternalMerchantId;
     private Set<TerminalWls> terminalWlses = new HashSet<TerminalWls>(0);

    public MerchantWls() {
    }

	
    public MerchantWls(String merchantId, String referenceNo, String onboardingStatus) {
        this.merchantId = merchantId;
        this.referenceNo = referenceNo;
        this.onboardingStatus = onboardingStatus;
    }
    public MerchantWls(Merchants merchants, String merchantId, String referenceNo, String onboardingStatus, String wlInternalMerchantId, Set<TerminalWls> terminalWlses) {
       this.merchants = merchants;
       this.merchantId = merchantId;
       this.referenceNo = referenceNo;
       this.onboardingStatus = onboardingStatus;
       this.wlInternalMerchantId = wlInternalMerchantId;
       this.terminalWlses = terminalWlses;
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
    @JoinColumn(name="baseMerchantId")
    public Merchants getMerchants() {
        return this.merchants;
    }
    
    public void setMerchants(Merchants merchants) {
        this.merchants = merchants;
    }

    
    @Column(name="merchantId", nullable=false)
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    
    @Column(name="referenceNo", nullable=false)
    public String getReferenceNo() {
        return this.referenceNo;
    }
    
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    
    @Column(name="onboardingStatus", nullable=false)
    public String getOnboardingStatus() {
        return this.onboardingStatus;
    }
    
    public void setOnboardingStatus(String onboardingStatus) {
        this.onboardingStatus = onboardingStatus;
    }

    
    @Column(name="wlInternalMerchantId")
    public String getWlInternalMerchantId() {
        return this.wlInternalMerchantId;
    }
    
    public void setWlInternalMerchantId(String wlInternalMerchantId) {
        this.wlInternalMerchantId = wlInternalMerchantId;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="merchantWls")
    public Set<TerminalWls> getTerminalWlses() {
        return this.terminalWlses;
    }
    
    public void setTerminalWlses(Set<TerminalWls> terminalWlses) {
        this.terminalWlses = terminalWlses;
    }




}


