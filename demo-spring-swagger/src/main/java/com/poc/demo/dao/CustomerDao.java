package com.poc.demo.dao;

import com.poc.demo.model.Customer;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CustomerDao class used to handle database (read, write, create and delete) activities.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Repository
public class CustomerDao {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomerDao.class);
    private Map<String, Customer> customers = new HashMap<>();

    /**
     * Method to save the customer.
     *
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        log.info("Customer: {}", customer.toString());
        customers.put(customer.getCustomerId(), customer);
    }

    /**
     * Method to update the existing customer.
     *
     * @param customer
     * @param customerId
     */
    public void updateCustomer(Customer customer, String customerId) {
        log.info("Customer to update by customerId: {}", customerId);
        customers.put(customerId, customer);
    }

    /**
     * Method to delete customer by customerId.
     *
     * @param customerId
     */
    public void deleteCustomer(String customerId) {
        log.info("Deleting customer by customerId: {}", customerId);
        customers.remove(customerId);
    }

    /**
     * Method to find all customers.
     *
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Customer> findAllCustomers() {
        List<Customer> allCustomers = customers.values().stream().collect(Collectors.toList());
        return allCustomers;
    }

    /**
     * Method to find customer by customerId.
     *
     * @param customerId
     * @return Customer
     */
    public Customer findCustomerById(String customerId) {
        log.info("CustomerId to search: {}", customerId);
        Customer searchedCustomer = customers.get(customerId);
        return searchedCustomer;
    }
}
