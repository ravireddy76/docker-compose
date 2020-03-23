package com.poc.cluster.service;

import com.poc.cluster.model.Cluster;
import com.poc.cluster.model.DataRequest;
import com.poc.cluster.model.DataResponse;
import com.poc.cluster.model.DataResult;
import com.poc.cluster.model.Point;
import com.poc.cluster.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * ClusterService class used to compute/calculate .
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class ClusterService {

    private List<Point> points;
    private List<Cluster> clusters;
    private List<DataResult> dataResults;

    /**
     * Method to create and compute/calculate cluster information.
     *
     * @param dataRequest
     */
    public void createAndComputeClusters(DataRequest dataRequest) throws Exception {
        log.info("Creating and compute clusters based in data points.");
        create(dataRequest);
        compute(dataRequest);

        /* Logging created data points and cluster information.*/
        String createdData = JsonUtils.serializeJson(dataResults);
        log.info("=>=>=>=>  Created data points and computed cluster information. =>=>=>=>");
        log.info(createdData);
        log.info("==============================================================================");
    }

    /**
     * Method to get cluster by iterationId and cluster id.
     *
     * @param iterationId
     * @param clusterId
     * @return DataResponse
     */
    public DataResponse getClusterById(int iterationId, int clusterId) {
        log.info("Search cluster by interationId: {}, clusterId: {}", iterationId, clusterId);

        DataResponse dataResponse = new DataResponse();
        for (DataResult dataResult : dataResults) {
            /* Search and get the cluster information for given iteration id and cluster id. */
            if (dataResult.getIteration() == iterationId) {
                dataResponse.setIteration(dataResult.getIteration());
                dataResponse.setCentroidDistance(dataResult.getCentroidDistance());

                /* Search and get the cluster for given cluster id. */
                for (Cluster cluster : dataResult.getClusters()) {
                    if (cluster.getId() == clusterId) {
                        dataResponse.setCluster(cluster);
                    }
                }
            }
        }

        return dataResponse;
    }

    /**
     * Method to create data points, cluster and centroids.
     *
     * @param dataRequest
     */
    private void create(DataRequest dataRequest) {
        /** Create points. */
        points = Point.createRandomPoints(dataRequest.getMinCoordinate(), dataRequest.getMaxCoordinate(), dataRequest.getNumPoints());
        clusters = new ArrayList<>();

        /** Create clusters and set random centroids. */
        for (int iter = 0; iter < dataRequest.getNumClusters(); iter++) {
            Cluster cluster = new Cluster(iter);
            Point centroid = Point.createRandomPoint(dataRequest.getMinCoordinate(), dataRequest.getMaxCoordinate());
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
    }

    /**
     * Method to compute/calculate the cluster, centroid information for given data points in iterative process
     * until centroid is computed to closest data points.
     *
     * @param dataRequest
     */
    private void compute(DataRequest dataRequest) {
        dataResults = new ArrayList<>();
        boolean finish = false;
        int iteration = 0;

        /** Add in new data, one at a time, recalculating centroids with each new one. */
        while (!finish) {

            /* Clear cluster state. */
            clearClusters();

            /* Get the previous centroids. */
            List<Point> lastCentroids = getCentroids();

            /* Assign points to the closer cluster. */
            assignCluster(dataRequest.getNumClusters());

            /* Calculate new centroids. */
            calculateCentroids();

            /* Get the current centroids. */
            List<Point> currentCentroids = getCentroids();
            iteration++;

            /* Calculates total distance between new and old centroids. */
            double distance = 0;
            for (int iter = 0; iter < lastCentroids.size(); iter++) {
                distance += Point.distance(lastCentroids.get(iter), currentCentroids.get(iter));
            }

            /* Build the data results information. */
            DataResult dataResult = buildDataResult(iteration, distance, dataRequest.getNumClusters());
            dataResults.add(dataResult);

            /* Validate or check if distance is zero; if distance is zero we can stop computing.*/
            if (distance == 0) {
                finish = true;
            }
        }
    }


    /**
     * Method to calculate and collect centroids for data points.
     */
    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<>();
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(), aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    /**
     * Method to compute and assign cluster for data points.
     *
     * @param numberOfClusters
     */
    private void assignCluster(int numberOfClusters) {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for (Point point : points) {
            min = max;
            for (int iter = 0; iter < numberOfClusters; iter++) {
                Cluster c = clusters.get(iter);
                distance = Point.distance(point, c.getCentroid());
                if (distance < min) {
                    min = distance;
                    cluster = iter;
                }
            }
            /* Set the cluster number for the points.*/
            point.setClusterNumber(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    /**
     * Method to calculate centroids for given data cluster.
     */
    private void calculateCentroids() {
        for (Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int nPoints = list.size();

            /* Calculate x and y coordinates summation. */
            for (Point point : list) {
                sumX += point.getX();
                sumY += point.getY();
            }

            /* Compute for point centroids.*/
            Point centroid = cluster.getCentroid();
            if (nPoints > 0) {
                double newX = sumX / nPoints;
                double newY = sumY / nPoints;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }

    /**
     * Method to plot or build the computed clusters information.
     *
     * @param numberOfClusters
     * @return List
     */
    private List<Cluster> plotClusters(int numberOfClusters) {
        List<Cluster> computedClusters = new ArrayList<>();
        for (int iter = 0; iter < numberOfClusters; iter++) {
            Cluster cluster = clusters.get(iter);
            computedClusters.add(cluster.plotCluster());
        }

        return computedClusters;
    }

    /**
     * Method to build the computed data results.
     *
     * @param iteration
     * @param centroidDistance
     * @param numOfClusters
     * @return DataResult
     */
    private DataResult buildDataResult(int iteration, double centroidDistance, int numOfClusters) {
        DataResult dataResult = new DataResult();
        dataResult.setIteration(iteration);
        dataResult.setCentroidDistance(centroidDistance);
        dataResult.setClusters(plotClusters(numOfClusters));
        return dataResult;
    }

    /**
     * Method to clear the clusters.
     */
    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

}
