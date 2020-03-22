package com.optum.hub.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClaimEvent message payload computed data and ingest into mongoDB
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
public class ClaimEvent {

    private String memberId;
    private String claimInstCode;
    private String eventDate;
}
