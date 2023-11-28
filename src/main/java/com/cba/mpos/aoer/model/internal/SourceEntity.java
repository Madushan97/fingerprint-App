package com.cba.mpos.aoer.model.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Table(name = "vw_download")
public class SourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID")
    private long LogID;

    @Column(name = "EmpID")
    private String EmpID;

    @Column(name = "LogDateTime")
    private Timestamp LogDateTime;

    @Column(name = "IsProcessed")
    private Boolean IsProcessed;

    @Column(name = "IpAdrs")
    private String IpAdrs;

    @Column(name = "DeviceId")
    private Long DeviceId;

    @Column(name = "checktype")
    private String checkType;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "SN")
    private String SN;
}
