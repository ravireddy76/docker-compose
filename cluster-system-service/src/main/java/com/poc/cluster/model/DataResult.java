package com.poc.cluster.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * DataResult class used to hold for search results.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
public class DataResult {

    private int iteration;
    private double centroidDistance;
    private List<Cluster> clusters;
}
