package com.mn.poc.listener;


import com.mn.poc.model.Order;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(groupId = "${topic.shippingGroupId}")
public class ShippingListener implements OrderListener {

    @Override
    @Topic("${topic.orderTp}")
    public void consumeMessage(@Body Order order) {
        log.info("ShippingListener: Consumed message order: {}", order);
    }
}
