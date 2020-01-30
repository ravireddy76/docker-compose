package com.mn.poc.listener;

import com.mn.poc.model.Order;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import io.micronaut.messaging.annotation.Header;

/**
 * OrderProducer interface used to publish messages into kafka topic.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@KafkaClient
public interface OrderProducer {

    @Topic("${topic.orderTp}")
    void publish(@Body Order order, @Header("Transaction-Type") String transactionType);
}
