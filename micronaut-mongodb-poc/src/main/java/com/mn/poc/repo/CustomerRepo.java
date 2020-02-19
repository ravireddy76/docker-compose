package com.mn.poc.repo;

import com.mn.poc.model.Customer;
import com.mn.poc.util.MongoTxClient;
import io.micronaut.data.annotation.Repository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.inject.Inject;

import java.util.List;


/**
 * CustomerRepo class used to handle database (read, write, create and delete) activities.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Repository
public class CustomerRepo {

    @Inject
    private MongoTxClient mongoTxClient;


    /**
     * Method to save the customer.
     *
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        log.info("Customer: {}", customer.toString());
        mongoTxClient.saveOrUpdate(customer);
    }

    /**
     * Method to update the existing customer.
     *
     * @param customer
     * @param customerId
     */
    public void updateCustomer(Customer customer, String customerId) {
        log.info("Customer to update by customerId: {}", customerId);
        mongoTxClient.saveOrUpdate(customer);
    }

    /**
     * Method to delete customer by customerId.
     *
     * @param customerId
     */
    public void deleteCustomer(String customerId) {
        log.info("Deleting customer by customerId: {}", customerId);
       // customers.remove(customerId);
    }

    /**
     * Method to find all customers.
     *
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Document> findAllCustomers() throws Exception {
        List<Document> allCustomers = mongoTxClient.findAllCustomers().blockingGet();
        return allCustomers;
    }

    /**
     * Method to find customer by customerId.
     *
     * @param customerId
     * @return Customer
     */
    public Customer findCustomerById(String customerId) throws Exception {
        log.info("CustomerId to search: {}", customerId);
        Customer searchedCustomer = mongoTxClient.findByCustomerId(customerId);
        return searchedCustomer;
    }

}
