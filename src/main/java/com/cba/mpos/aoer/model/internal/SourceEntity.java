package com.cba.mpos.aoer.model.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Table(name = "vw_download")
public class SourceEntity {

    @Id
    @Column(name = "LogID")
    private Long LogID;

    @Column(name = "EmpID")
    private String EmpID;

    @Column(name = "LogDateTime")
    private Date LogDateTime;

    @Column(name = "IsProcessed")
    private Byte IsProcessed;

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
