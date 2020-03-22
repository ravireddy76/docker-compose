package com.optum.hub.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DataResult entity is to save computed data into mongoDB
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
@Document
public class DataResult {

    @Id
    private String id;
    private String memberId;
    private String memberName;
    private String claimId;
    private String claimInstCode;
    private String createdDate;
}
