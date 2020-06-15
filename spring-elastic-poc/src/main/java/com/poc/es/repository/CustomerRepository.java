package com.poc.es.repository;

import com.poc.es.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    List<Customer> findByCustomerId(String customerId);

    List<Customer> findByZipCode(String zipCode);
}
