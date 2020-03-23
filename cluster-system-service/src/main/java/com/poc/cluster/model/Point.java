package com.poc.cluster.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Point class used to compute or calculate for the given data points.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Data
public class Point {
    private double x = 0;
    private double y = 0;
    private int clusterNumber = 0;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Calculates the distance between two points. */
    public static double distance(Point point, Point centroid) {
        /* Calculating euclidean distance  using equation =>> d(p,q) = SQRT(SQ[q1-p1] + SQ[q2-p2]). */
        return Math.sqrt(Math.pow((centroid.getY() - point.getY()), 2) + Math.pow((centroid.getX() - point.getX()), 2));
    }

    /** Creates random point */
    public static Point createRandomPoint(int min, int max) {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }

    public static List<Point> createRandomPoints(int min, int max, int number) {
        List<Point> points = new ArrayList<>();
        for (int iter = 0; iter <= number; iter++) {
            points.add(createRandomPoint(min, max));
        }
        return points;
    }
}
