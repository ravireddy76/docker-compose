package com.poc.solr.controller;

import com.poc.solr.model.Order;
import com.poc.solr.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/solrpoc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/solrpoc", description = "API to handle order CRUD operations")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Method to create order.
     *
     * @param order
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Creating new Order")
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> create(@RequestBody Order order) {
        try {
            log.info("Creating new order: {}", order.toString());
            Order createdOrder = orderService.create(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or register Order: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to update Order.
     *
     * @param order
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Updating existing Order")
    @RequestMapping(value = "/order", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> update(@RequestBody Order order) {
        try {
            log.info("Updating existing order: {}", order.toString());
            Order updatedOrder = orderService.update(order);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to updating existing order: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to delete Order.
     *
     * @param orderId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Updating existing Order")
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(
            @ApiParam(value = "OrderId", required = true) @PathVariable("orderId") String orderId) {
        try {
            log.info("Deleting existing Order by id: {}", orderId);
            String response = orderService.delete(new Long(orderId));
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to delete Order by orderId: {}, with exception: {}", orderId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Method to get order by orderId.
     *
     * @param orderId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get order by order id")
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        try {
            log.info("Search order by orderId: {}", orderId);
            Order searchedOrder = orderService.getOrderById(new Long(orderId));
            return new ResponseEntity<>(searchedOrder, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get order by orderId: {}, with exception: {}", orderId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Method to get order by search term and page by custom query.
     *
     * @param searchTerm
     * @param page
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get order by order id")
    @RequestMapping(value = "/order/search/custom/{searchTerm}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrderByCustomSearchTerm(@PathVariable String searchTerm, @PathVariable int page) {
        try {
            log.info("Search order by custom query search term: {}, page number: {}", searchTerm, page);
            List<Order> searchedOrders = orderService.getOrdersByCustomQuery(searchTerm, page);
            return new ResponseEntity<>(searchedOrders, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get order by search term: {}, page number: {}, with exception: {}", searchTerm, page, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get order by search term and page by named query.
     *
     * @param searchTerm
     * @param page
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get order by order id")
    @RequestMapping(value = "/order/search/named/{searchTerm}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrderByNamedSearchTerm(@PathVariable String searchTerm, @PathVariable int page) {
        try {
            log.info("Search order by named query with search term: {}, page number: {}", searchTerm, page);
            List<Order> searchedOrders = orderService.getOrdersByNamedQuery(searchTerm, page);
            return new ResponseEntity<>(searchedOrders, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get order by search term: {}, page number: {}, with exception: {}", searchTerm, page, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Method to get orders by product name using facets.
     *
     * @param page
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get order by order id")
    @RequestMapping(value = "/order/search/named/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrderByProductNameFacet(@PathVariable int page) {
        try {
            log.info("Search orders by product name using facets for page number: {}", page);
            List<Order> searchedOrders = orderService.getAllFacetsByProductName(page);
            return new ResponseEntity<>(searchedOrders, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get orders by product name using facets for page number: {}, with exception: {}", page, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
