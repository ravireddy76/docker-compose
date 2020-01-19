package com.poc.demo.service;

import com.poc.demo.dao.CustomerDao;
import com.poc.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomerService class acts like delegate between controller and dao layer to handle business cases.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Service
public class CustomerService {

    @Autowired
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
