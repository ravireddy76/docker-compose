package com.poc.kafka.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private String id;
    private String description;
    private Date created;
}
