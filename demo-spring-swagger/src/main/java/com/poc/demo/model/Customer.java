package com.poc.demo.model;

import lombok.Data;

/**
 * Customer class used as a POJO to hold and handle customer related data.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Data
public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String contactNumber;

}
