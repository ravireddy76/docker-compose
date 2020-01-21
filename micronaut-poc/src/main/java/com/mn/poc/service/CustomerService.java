package com.mn.poc.service;


import com.mn.poc.dao.CustomerDao;
import com.mn.poc.model.Customer;
import lombok.extern.slf4j.Slf4j;

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
    CustomerDao customerDao;

    /**
     * Method to create customer.
     *
     * @param customer
     * @return Customer
     */
    public Customer createCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
        return customer;
    }

    /**
     * Method to update customer.
     *
     * @param customer
     * @return Customer
     */
    public Customer updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer, customer.getCustomerId());
        return customer;
    }

    /**
     * Method to delete customer.
     *
     * @param customerId
     */
    public void deleteCustomer(String customerId) {
        customerDao.deleteCustomer(customerId);
    }

    /**
     * Method to get all customers.
     *
     * @return List
     */
    public List<Customer> getAllCustomers() {
        return customerDao.findAllCustomers();
    }


    /**
     * Method to get customer by customerId.
     *
     * @param customerId
     * @return Customer
     */
    public Customer getCustomerById(String customerId) {
        Customer searchedCustomer = customerDao.findCustomerById(customerId);
        return searchedCustomer;
    }

}
