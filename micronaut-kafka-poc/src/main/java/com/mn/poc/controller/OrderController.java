package com.mn.poc.controller;

import com.mn.poc.model.Order;
import com.mn.poc.service.OrderService;
import io.micronaut.context.annotation.Context;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@Context
@Controller(value = "/kafkapoc")
public class OrderController {

    @Inject
    OrderService orderService;

    /**
     * Method to create order.
     *
     * @param order
     * @return ResponseEntity
     */
    @Post(uri = "/order", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Order create(@Body Order order) {
        log.info("Creating  new order: {}", order.toString());
        Order createdOrder = orderService.createOrder(order);
        return createdOrder;
    }

    /**
     * Method to update order.
     *
     * @param order
     * @return ResponseEntity
     */
    @Put(uri = "/order", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Order update(@Body Order order) {
        log.info("Updating existing order: {}", order.toString());
        Order updatedOrder = orderService.updateOrder(order);
        return updatedOrder;
    }

    /**
     * Method to delete order.
     *
     * @param orderId
     * @return ResponseEntity
     */
    @Delete(uri = "/order/{orderId}")
    public String delete(@PathVariable("orderId") String orderId) {
        log.info("Deleting existing order by id: {}", orderId);
        orderService.deleteOrder(orderId);
        return "Order information is deleted by id :" + orderId;
    }

    /**
     * Method to get order by orderId.
     *
     * @param orderId
     * @return ResponseEntity
     */
    @Get(uri = "/order/{orderId}", produces = MediaType.APPLICATION_JSON)
    public Order getOrderById(@PathVariable String orderId) {
        log.info("Search order by orderId : " + orderId);
        Order searchedOrder = orderService.getOrderById(orderId);
        return searchedOrder;
    }

    /**
     * Method to get all orders.
     *
     * @return List
     */
    @Get(uri = "/order/all", produces = MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() {
        log.info("Search all orders");
        List<Order> orders = orderService.getAllOrders();
        return orders;
    }

}
