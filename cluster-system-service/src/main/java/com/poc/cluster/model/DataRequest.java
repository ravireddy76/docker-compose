package com.poc.cluster.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataRequest class used to create data points and clusters through user request.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
public class DataRequest {

    private int numClusters;
    private int numPoints;
    private int minCoordinate;
    private int maxCoordinate;
}
