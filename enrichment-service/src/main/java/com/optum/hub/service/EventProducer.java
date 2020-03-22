package com.optum.hub.service;


import com.optum.hub.model.ClaimEvent;
import com.optum.hub.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * EventProducer class used to publish message into given kafka topic.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class EventProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name}")
    private String topic;

    /**
     * Method to publish message into given topic.
     *
     * @param claimEvent
     * @throws Exception
     */
    public void publish(ClaimEvent claimEvent) throws Exception {
        log.info("sending message='{}' to topic='{}'", claimEvent.toString(), topic);

        /* Translate message object to json message. */
        String jsonMessage = JsonUtils.serializeJson(claimEvent);
        kafkaTemplate.send(topic, jsonMessage);
    }

}
