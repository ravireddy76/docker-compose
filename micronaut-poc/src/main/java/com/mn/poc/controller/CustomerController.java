package com.mn.poc.controller;

import com.mn.poc.model.Customer;
import com.mn.poc.service.CustomerService;


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
@Controller(value = "/demopoc")
public class CustomerController {

    @Inject
    CustomerService customerService;

    /**
     * Method to create customer.
     *
     * @param customer
     * @return ResponseEntity
     */
    @Post(uri = "/customer", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Customer create(@Body Customer customer) {
        log.info("Creating  new customer: {}", customer.toString());
        Customer registeredCustomer = customerService.createCustomer(customer);
        return registeredCustomer;
    }

    /**
     * Method to update customer.
     *
     * @param customer
     * @return ResponseEntity
     */
    @Put(uri = "/customer", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Customer update(@Body Customer customer) {
        log.info("Updating existing customer: {}", customer.toString());
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return updatedCustomer;
    }

    /**
     * Method to delete customer.
     *
     * @param customerId
     * @return ResponseEntity
     */
    @Delete(uri = "/customer/{customerId}")
    public String delete(@PathVariable("customerId") String customerId) {
        log.info("Deleting existing customer by id: {}", customerId);
        customerService.deleteCustomer(customerId);
        return "Customer information is deleted by id :" + customerId;
    }

    /**
     * Method to get customer by customerId.
     *
     * @param customerId
     * @return ResponseEntity
     */
    @Get(uri = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathVariable String customerId) {
        log.info("Search customer by customerId : " + customerId);
        Customer searchedCustomer = customerService.getCustomerById(customerId);
        return searchedCustomer;
    }

    /**
     * Method to get all customers.
     *
     * @return ResponseEntity
     */
    @Get(uri = "/customer/all", produces = MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        log.info("Search all customers");
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

}
