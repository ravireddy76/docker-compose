package com.mn.poc.service;

import com.mn.poc.model.Customer;
import com.mn.poc.repo.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * CustomerService class acts like delegate between controller and dao layer to handle business cases.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Singleton
public class CustomerService {

    @Inject
    CustomerRepo customerRepo;

    /**
     * Method to create customer.
     *
     * @param customer
     * @return Customer
     */
    public Customer createCustomer(Customer customer) {
        customerRepo.saveCustomer(customer);
        return customer;
    }

    /**
     * Method to update customer.
     *
     * @param customer
     * @return Customer
     */
    public Customer updateCustomer(Customer customer) {
        customerRepo.updateCustomer(customer, customer.getCustomerId());
        return customer;
    }

    /**
     * Method to delete customer.
     *
     * @param customerId
     */
    public void deleteCustomer(String customerId) {
        customerRepo.deleteCustomer(customerId);
    }

    /**
     * Method to get all customers.
     *
     * @return List
     */
    public List<Document> getAllCustomers() throws Exception {
        return customerRepo.findAllCustomers();
    }


    /**
     * Method to get customer by customerId.
     *
     * @param customerId
     * @return Customer
     */
    public Customer getCustomerById(String customerId) throws Exception {
        Customer searchedCustomer = customerRepo.findCustomerById(customerId);
        return searchedCustomer;
    }

}
