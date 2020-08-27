package com.poc.solr.service;

import com.poc.solr.model.Order;
import com.poc.solr.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderService class acts like delegate between controller and repository layer to handle business cases.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class OrderService {

    @Value("${page.size}")
    private int pageSize;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Method to create order and ingest data into solr collection.
     *
     * @param order
     * @return Order
     */
    public Order create(Order order) {
        log.info("Ingesting the order into solr");
        Order createdOrder = orderRepository.save(order);
        return createdOrder;
    }

    /**
     * Method to update order and ingest data into solr collection.
     *
     * @param order
     * @return Order
     */
    public Order update(Order order) {
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }

    /**
     * Method to delete order information from solr collection for given order id.
     *
     * @param orderId
     * @return String
     */
    public String delete(Long orderId) {
        Order orderToDelete = orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderToDelete);
        String deleteMsg = "Order got deleted for orderId: ".concat(String.valueOf(orderToDelete.getOrderId()));
        return deleteMsg;
    }

    /**
     * Method to search order by order id.
     *
     * @param orderId
     * @return Order
     */
    public Order getOrderById(Long orderId) {
        Order searchedOrder = orderRepository.findByOrderId(orderId);
        return searchedOrder;
    }

    /**
     * Method to search orders by description with pagination.
     *
     * @param descSearchTerm
     * @param pageNumber
     * @return List
     */
    public List<Order> getOrdersByDesc(String descSearchTerm, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Order> searchedOrders = orderRepository.findByDescription(descSearchTerm, pageRequest).getContent();
        return searchedOrders;
    }

    /**
     * Method to search orders by custom query with pagination.
     *
     * @param searchTerm
     * @param pageNumber
     * @return List
     */
    public List<Order> getOrdersByCustomQuery(String searchTerm, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Order> searchedOrders = orderRepository.findByCustomQuery(searchTerm, pageRequest).getContent();
        return searchedOrders;
    }

    /**
     * Method to search orders by named query with pagination.
     *
     * @param searchTerm
     * @param pageNumber
     * @return List
     */
    public List<Order> getOrdersByNamedQuery(String searchTerm, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Order> searchedOrders = orderRepository.findByNamedQuery(searchTerm, pageRequest).getContent();
        return searchedOrders;
    }

    /**
     * Method to search orders by named query with pagination.
     *
     * @param pageNumber
     * @return List
     */
    public List<Order> getAllFacetsByProductName(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Order> searchedOrders   = orderRepository.findAllFacetsByProductName(pageRequest).getContent();
        return searchedOrders;
    }

}
