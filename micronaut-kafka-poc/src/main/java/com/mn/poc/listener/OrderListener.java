package com.mn.poc.listener;

import com.mn.poc.model.Order;
import io.micronaut.messaging.annotation.Body;

/**
 * OrderListener interface used to consume (receive) messages from kafka topic.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
public interface OrderListener {

    /**
     * Method to read or consume message from topic for given group id.
     *
     * @param order
     */
    void consumeMessage(@Body Order order);
}
