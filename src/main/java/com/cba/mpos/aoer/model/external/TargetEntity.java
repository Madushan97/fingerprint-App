package com.cba.mpos.aoer.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "hr_pay_attendance_raw_data")
public class TargetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrimary")
    private Long id;

    @Column(name = "DateIndex")
    private String dateIndex;

    @Column(name = "timeIndex")
    private String timeIndex;

    @Column(name = "employee_idIndex")
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
