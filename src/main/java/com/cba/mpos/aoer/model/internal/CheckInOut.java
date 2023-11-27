package com.cba.mpos.aoer.model.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "checkinout")
public class CheckInOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userid")
    private int userid;

    @Column(name = "checktime")
    private Timestamp checktime;

    @Column(name = "checktype")
    private String checktype;

    @Column(name = "verifycode")
    private int verifycode;

    @Column(name = "SN")
    private String SN;

    @Column(name = "sensorid")
    private String sensorid;

    @Column(name = "WorkCode")
    private String WorkCode;

    @Column(name = "Reserved")
    private String Reserved;

    @Column(name = "IsProcessed")
    private Byte IsProcessed;
}
