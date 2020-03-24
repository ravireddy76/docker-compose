package com.play.poc.ml;

import java.util.ArrayList;
import java.util.List;

public class KMeanOptClient {

    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;
    //Number of Points
    private int NUM_POINTS = 15;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 10;

    private List<Point> points;
    private List<Cluster> clusters;
    private List<Result> results;

    public static void main(String[] args){
        KMeanOptClient kmeans = new KMeanOptClient();
        kmeans.init();
        kmeans.calculate();

        System.out.println(">=>=>=> Results =>=>=>");
        for (Result result: kmeans.getResults()) {
            System.out.println("Iteration :: "+result.getIteration());
            System.out.println("Centroid Distance :: "+result.getCentroidDistance());

            for (Cluster cluster: result.getClusters()) {
                System.out.println("Cluster:: "+cluster.toString());
            }

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

    //Initializes the process
    public void init() {
        //Create Points
        points = Point.createRandomPoints(MIN_COORDINATE,MAX_COORDINATE,NUM_POINTS);

        clusters = new ArrayList<>();
        //Create Clusters
        //Set Random Centroids
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(MIN_COORDINATE,MAX_COORDINATE);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //Print Initial state
       // plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }


    private List<Cluster> plotClusterObjs() {
        List<Cluster> computedClusters = new ArrayList<>();
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster c = clusters.get(i);
            //c.plotCluster();
            computedClusters.add(c.plotClusterObj());
        }

        return computedClusters;
    }


    //The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        results = new ArrayList<>();

        // Add in new data, one at a time, recalculating centroids with each new one.
        while(!finish) {
            //Clear cluster state
            clearClusters();

            List<Point> lastCentroids = getCentroids();

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateCentroids();

            List<Point> currentCentroids = getCentroids();
            iteration++;

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
            }
//            System.out.println("#################");
//            System.out.println("Iteration: " + iteration);
//            System.out.println("Centroid distances: " + distance);
//            plotClusters();

            Result result = new Result();
            result.setIteration(iteration);
            result.setCentroidDistance(distance);
            result.setClusters(plotClusterObjs());
            results.add(result);
            //plotClusters();

            if(distance == 0) {
                finish = true;
            }
        }

        setResults(results);
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    private void clearClusters() {
        for(Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<>();
        for(Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(),aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for(Point point : points) {
            min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster_number(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int n_points = list.size();

            for(Point point : list) {
                sumX += point.getX();
                sumY += point.getY();
            }

            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
                double newX = sumX / n_points;
                double newY = sumY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }

}
