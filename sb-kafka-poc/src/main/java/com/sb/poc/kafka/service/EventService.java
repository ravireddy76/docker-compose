package com.sb.poc.kafka.service;

import com.sb.poc.kafka.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * EventService class used to publish and consume events for a given kafka topic.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class EventService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


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
        String messageToPublish = JsonUtils.serializeJson(message);
        kafkaTemplate.send(topic, messageToPublish);
    }


    /**
     * Method to consume message from test topic.
     *
     * @param messageToConsume
     */
    @KafkaListener(topics = "${topics.test}")
    public void testTopicListener(@Payload String messageToConsume){
        log.info(">>>>>>> Consumed Message: {}", messageToConsume);
    }

}
