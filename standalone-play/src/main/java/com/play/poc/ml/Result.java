package com.play.poc.ml;

import java.util.List;

public class Result  {

    private int iteration;
    private double centroidDistance;
    private List<Cluster> clusters;

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public double getCentroidDistance() {
        return centroidDistance;
    }

    public void setCentroidDistance(double centroidDistance) {
        this.centroidDistance = centroidDistance;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    @Override
    public String toString() {
        return "Result{" +
                "iteration=" + iteration +
                ", centroidDistance=" + centroidDistance +
                ", clusters=" + clusters +
                '}';
    }
}
