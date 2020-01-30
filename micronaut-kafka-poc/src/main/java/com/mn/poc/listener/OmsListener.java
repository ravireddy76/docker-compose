package com.mn.poc.listener;

import com.mn.poc.model.Order;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(groupId = "${topic.omsGroupId}")
public class OmsListener implements OrderListener {

    @Override
    @Topic("${topic.orderTp}")
    public void consumeMessage(@Body Order order) {
        log.info("OmsListener: Consumed message order: {}", order);
    }
}
