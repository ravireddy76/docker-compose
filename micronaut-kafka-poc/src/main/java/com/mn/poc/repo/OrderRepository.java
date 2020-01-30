package com.mn.poc.repo;

import com.mn.poc.model.Order;
import io.micronaut.data.annotation.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * OrderRepository class used to handle read, write, create and delete activities of order in memory.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Repository
public class OrderRepository {

    private Map<String, Order> orders = new HashMap<>();

    /**
     * Method to save the order.
     *
     * @param order
     */
    public void saveOrder(Order order) {
        log.info("Order: {}", order.toString());
        orders.put(order.getId(), order);
    }

    /**
     * Method to update the existing order.
     *
     * @param order
     * @param orderId
     */
    public void updateOrder(Order order, String orderId) {
        log.info("Order to update by orderId: {}", orderId);
        orders.put(orderId, order);
    }

    /**
     * Method to delete order by orderId.
     *
     * @param orderId
     */
    public void deleteOrder(String orderId) {
        log.info("Deleting order by orderId: {}", orderId);
        orders.remove(orderId);
    }

    /**
     * Method to find all orders.
     *
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Order> findAllOrders() {
        List<Order> allOrders = orders.values().stream().collect(Collectors.toList());
        return allOrders;
    }

    /**
     * Method to find customer by orderId.
     *
     * @param orderId
     * @return Order
     */
    public Order findCustomerById(String orderId) {
        log.info("CustomerId to search: {}", orderId);
        Order searchedOrder = orders.get(orderId);
        return searchedOrder;
    }

}
