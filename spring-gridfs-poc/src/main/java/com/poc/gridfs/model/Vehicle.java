package com.poc.gridfs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vehicle class used to hold vechile information from yaml file configuration.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
public class Vehicle {

    private String brand;
    private String model;
    private String description;
}
