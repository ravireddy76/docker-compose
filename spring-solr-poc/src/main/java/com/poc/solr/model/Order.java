package com.poc.solr.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@NoArgsConstructor
@SolrDocument(collection="orders")
public class Order {

    @Id
    @Indexed(name = "oid", type = "long")
    private Long orderId;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "desc", type = "string")
    private String description;

    @Indexed(name = "pname", type = "string")
    private String productName;

    @Indexed(name = "cname", type = "string")
    private String customerName;

    @Indexed(name = "cmobile", type = "string")
    private String customerMobile;
}
