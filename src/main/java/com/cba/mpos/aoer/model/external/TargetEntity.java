package com.cba.mpos.aoer.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
    private Date dateIndex;

    @Column(name = "time")
    private Time timeIndex;

    @Column(name = "employee_id")
    private String employeeIdIndex;

    @Column(name = "action")
    private String action;

    @Column(name = "action_hbx")
    private String actionHbx;

    @Column(name = "`check`")
    private String check;

    @Column(name = "status")
    private String status;

    @Column(name = "created_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdTime;

    @Column(name = "update_time")
    private Timestamp updateTime;
}
