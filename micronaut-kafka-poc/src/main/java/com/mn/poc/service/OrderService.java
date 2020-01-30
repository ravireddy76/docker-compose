package com.mn.poc.service;

import com.mn.poc.listener.OrderProducer;
import com.mn.poc.model.Order;
import com.mn.poc.repo.OrderRepository;
import com.mn.poc.util.AppUtils;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * OrderService class acts like delegate between controller and listener/repo layers to handle business cases.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Singleton
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderProducer orderProducer;

    /**
     * Method to create order and save into repository and publish message into kafka topic.
     *
     * @param order
     * @return Order
     */
    public Order createOrder(Order order) {
        log.info("OrderService:: createOrder saving and publishing order information: {}", order);
        orderRepository.saveOrder(order);
        orderProducer.publish(order, AppUtils.CREATE_TRANS);
        return order;
    }

    /**
     * Method to update order by id and update into repository and publish message into kafka topic
     *
     * @param order
     * @return Order
     */
    public Order updateOrder(Order order) {
        log.info("OrderService:: updateOrder updating and publishing order information: {}", order);
        orderRepository.updateOrder(order, order.getId());
        orderProducer.publish(order, AppUtils.UPDATE_TRANS);
        return order;
    }


    /**
     * Method to delete the order.
     *
     * @param orderId
     */
    public void deleteOrder(String orderId) {
        log.info("OrderService:: deleteOrder delete order by id: {}", orderId);
        orderRepository.deleteOrder(orderId);
        // orderProducer.publish(ord);
    }

    /**
     * Method to get all orders information.
     *
     * @return List
     */
    public List<Order> getAllOrders() {
        log.info("OrderService:: getAllOrders");
        return orderRepository.findAllOrders();
    }

    /**
     * Method to get order by id.
     *
     * @param orderId
     * @return Order
     */
    public Order getOrderById(String orderId) {
        log.info("OrderService:: getOrderById");
        Order searchedOrder = orderRepository.findCustomerById(orderId);
        return searchedOrder;
    }
}
