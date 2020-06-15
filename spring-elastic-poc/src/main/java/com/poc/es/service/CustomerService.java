package com.poc.es.service;

import com.poc.es.model.Customer;
import com.poc.es.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerService class used to handle CRUD operations and save customer information into elastic search.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Method to create a customer.
     *
     * @param customer
     * @return Customer
     */
    public Customer create(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    /**
     * Method to update customer.
     *
     * @param customer
     * @return Customer
     */
    public Customer update(Customer customer) {
        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }

    /**
     * Method to get customer by id.
     *
     * @param customerId
     * @return Customer
     */
    public Customer getCustomerById(String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId).get(0);
        return customer;
    }

    /**
     * Method to get customer by zipcode.
     *
     * @param zipCode
     * @return List
     */
    public List<Customer> getCustomersByZipCode(String zipCode) {
        List<Customer> customers = customerRepository.findByZipCode(zipCode);
        return customers;
    }

}
