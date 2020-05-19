package com.poc.es.controller;

import com.poc.es.model.Customer;
import com.poc.es.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
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
@RequestMapping(value = "/demopoc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/demopoc", description = "API to handle customer CRUD operations")
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * Method to create customer.
     *
     * @param customer
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Creating new customer")
    @RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        try {
            log.info("Creating  new customer: {}", customer.toString());
            Customer registeredCustomer = customerService.create(customer);
            return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or register customer: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to update customer.
     *
     * @param customer
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Updating existing customer")
    @RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        try {
            log.info("Updating existing customer: {}", customer.toString());
            Customer updatedCustomer = customerService.update(customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to updating existing customer: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to delete customer.
     *
     * @param customerId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Delete existing customer")
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> delete(
            @ApiParam(value = "customerId", required = true) @PathVariable("customerId") String customerId) {
        try {
            log.info("Deleting existing customer by id: {}", customerId);
            //customerService.delete(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to delete customer by customerId: {}, with exception: {}", customerId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get customer by customerId.
     *
     * @param customerId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get customer by customer id")
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        try {
            log.info("Search customer by customerId : " + customerId);
            Customer searchedCustomer = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(searchedCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get customer by customerId: {}, with exception: {}", customerId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Method to get all customers by zipcode.
     *
     * @param zipCode
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get all customers by zipcode")
    @RequestMapping(value = "/customer/{zipCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getCustomersByZipCode(@PathVariable String zipCode) {
        try {
            log.info("Search all customers by zipcode");
            List<Customer> customers = customerService.getCustomersByZipCode(zipCode);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get all customers by zipcode exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
