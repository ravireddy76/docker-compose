package com.mn.poc.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order {

    private String id;
    private String type;
    private String address;
    private String status;
    private String createdDate;
}
