package com.cba.mpos.aoer.model.external;
// Generated Feb 15, 2023 9:07:45 AM by Hibernate Tools 3.6.0


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * ConsumingServices generated by hbm2java
 */
@Entity
@Table(name="ConsumingServices")
public class ConsumingServices  implements java.io.Serializable {


     private Integer id;
     private String serviceName;
     private Set<Subscriptions> subscriptionses = new HashSet<Subscriptions>(0);

    public ConsumingServices() {
    }

	
    public ConsumingServices(String serviceName) {
        this.serviceName = serviceName;
    }
    public ConsumingServices(String serviceName, Set<Subscriptions> subscriptionses) {
       this.serviceName = serviceName;
       this.subscriptionses = subscriptionses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="serviceName", nullable=false)
    public String getServiceName() {
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consumingServices")
    public Set<Subscriptions> getSubscriptionses() {
        return this.subscriptionses;
    }
    
    public void setSubscriptionses(Set<Subscriptions> subscriptionses) {
        this.subscriptionses = subscriptionses;
    }




}

