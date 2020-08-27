package com.poc.kafka.service;

import com.poc.kafka.model.Message;
import com.poc.kafka.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * EventPublisher class used to publish events or messages into given kafka topic.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class EventPublisher {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topics.multipp}")
    private String multippTopic;

    /**
     * Method to publish message into given topic.
     *
     * @param message
     * @throws Exception
     */
    public void publish(Message message) throws Exception {
        log.info("sending message='{}' to topic='{}'", message.toString(), multippTopic);

        /* Translate message object to json message. */
        String jsonMessage = JsonUtils.serializeJson(message);
        kafkaTemplate.send(multippTopic, jsonMessage);
    }


    /**
     * Method to publish message into given topic.
     * s
     * @param topic
     * @param message
     * @param <T>
     * @throws Exception
     */
    public <T> void publish(String topic, T message) throws Exception {
        log.info("sending message='{}' to topic='{}'", message.toString(), topic);

        /* Translate message object to json string. */
        String billingSummaryMsg = JsonUtils.serializeJson(message);
        kafkaTemplate.send(topic, billingSummaryMsg);
    }

}
