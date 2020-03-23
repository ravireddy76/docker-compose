package com.poc.cluster.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Cluster class used to compute centroids for given data points and plot the cluster.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Data
public class Cluster {

    public int id;
    public Point centroid;
    public List<Point> points;

    public Cluster(){ }

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList<>();
        this.centroid = null;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void clear() {
        points.clear();
    }

    /** Method to plot the cluster. */
    public Cluster plotCluster() {
        return this;
    }

}
