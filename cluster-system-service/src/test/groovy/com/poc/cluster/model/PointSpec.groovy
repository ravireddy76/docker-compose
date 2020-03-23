package com.poc.cluster.model

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class PointSpec extends Specification {
    Point samplePoint = new Point(1.1, 2.2)

    def "Test for getter/setter"() {
        when:
        def point = samplePoint

        then:
        point.getX() == 1.1
        point.getY() == 2.2
        point.getClusterNumber() == 0
    }

    def "Test for distance calculation scenario: #desc"() {
        given:
        Point point = new Point(p1, p2)
        Point centroid = new Point(q1, q2)

        when:
        def result = Point.distance(point, centroid)

        then:
        result == expectedResult

        where:
        desc                   | p1 | p2 | q1 | q2 || expectedResult
        "1 digit input values" | 2  | 3  | 4  | 5  || 2.8284271247461903
        "2 digit input values" | 22 | 33 | 44 | 55 || 31.11269837220809
    }

    def "Test for create random point"() {
        when:
        def result = Point.createRandomPoint(0, 20)

        then:
        result
    }

    def "Test for create random points"() {
        when:
        def result = Point.createRandomPoints(0, 20, 2)

        then:
        result
    }

}
