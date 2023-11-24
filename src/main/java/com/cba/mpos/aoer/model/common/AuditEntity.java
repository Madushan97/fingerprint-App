package com.cba.mpos.aoer.model.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public class AuditEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "DATETIME  DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    /*@Column(columnDefinition = "VARCHAR(30) DEFAULT 'system'", updatable = false)
    private String createdBy;*/

    @Column(columnDefinition = "DATETIME  DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /*@Column(columnDefinition = "VARCHAR(30) DEFAULT 'system'")
    private String updatedBy;*/

}
