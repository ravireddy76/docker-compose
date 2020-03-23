package com.poc.cluster.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataResponse class used as response object for search results.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
public class DataResponse {

    private int iteration;
    private double centroidDistance;
    private Cluster cluster;
}
