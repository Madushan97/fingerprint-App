package com.cba.mpos.aoer.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "hr_pay_attendance_raw_data")
public class TargetEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "employee_id")
    private String employee_id;

    @Column(name = "action", columnDefinition = "VARCHAR(11) DEFAULT '0'")
    private String action;

    @Column(name = "action_hbx", columnDefinition = "VARCHAR(11) DEFAULT '0'")
    private String action_hbx;

    @Column(name = "`check`", columnDefinition = "VARCHAR(11) DEFAULT '0'")
    private String check;

    @Column(name = "status", columnDefinition = "VARCHAR(11) DEFAULT '0'")
    private String status;

    @Column(name = "created_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created_time;

    @Column(name = "update_time", nullable = true, columnDefinition = "DATETIME DEFAULT NULL")
    private Timestamp update_time;

    @PrePersist
    public void prePersist() {
        created_time = Timestamp.from(Instant.now());
    }
}
