package com.poc.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
public class Customer {

    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String state;
    private String zipCode;
}
