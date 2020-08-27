package com.poc.kafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClaimsBilling entity is used to map the data of
 * <p>
 * <i> <b>Table = claims_billing </b> under <br/>
 * <b>Schema = poc_data </b> with in the enterprise application. </i>
 * </p>
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Entity
@Table(name = "claims_billing", schema = "poc_data")
@Data
@NoArgsConstructor
public class ClaimsBilling {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "memberid")
    private String memberId;

    @Column(name = "claimid")
    private String claimId;

    @Column(name = "amount")
    private Double amount;
}
