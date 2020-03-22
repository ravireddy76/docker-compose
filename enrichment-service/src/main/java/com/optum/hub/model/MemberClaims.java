package com.optum.hub.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MemberClaims entity is used to map the data of
 * <p>
 * <i> <b>Table = mbr_cliam </b> under <br/>
 * <b>Schema = mbrclms </b> with in the enterprise application. </i>
 * </p>
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Entity
@Table(name = "mbr_cliam", schema = "mbrclms")
@Data
@NoArgsConstructor
public class MemberClaims {

    @Id
    @Column(name = "id", nullable = false, length = 1000)
    private String id;

    @Column(name = "MBR_ID", nullable = false, length = 50)
    private String memberId;

    @Column(name = "MBR_NAME", nullable = false, length = 50)
    private String memberName;

    @Column(name = "CLM_ID", nullable = false, length = 50)
    private String claimId;

    @Column(name = "CLM_INST_CD", nullable = false, length = 10)
    private String claimInstCode;

    @Column(name = "CREATED_DT", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private Timestamp createdDate;

}
