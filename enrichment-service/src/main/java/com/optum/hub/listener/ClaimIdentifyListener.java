package com.optum.hub.listener;

import com.optum.hub.model.ClaimEvent;
import com.optum.hub.service.ClaimIdentifyService;
import com.optum.hub.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * ClaimIdentifyListener class used to consume message from kafka topic and search, compute claims and ingest data results into mongodb.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class ClaimIdentifyListener {

    @Autowired
    ClaimIdentifyService claimIdentifyService;


    @KafkaListener(topics = "${topic.name}")
    public void messageListener(@Payload String messagePayload) throws Exception {
        log.info("Message: {}", messagePayload);

        ClaimEvent claimEvent = JsonUtils.deserializeJson(ClaimEvent.class, messagePayload);
        claimIdentifyService.searchAndComputeClaims(claimEvent.getClaimInstCode());
        log.info("Member claims search, computed and ingested into mongodb database for claims instruction code: {}", claimEvent.getClaimInstCode());
    }

}
