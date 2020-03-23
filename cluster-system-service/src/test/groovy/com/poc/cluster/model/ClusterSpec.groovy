package com.poc.cluster.model

import spock.lang.Specification

class ClusterSpec extends Specification {
    Point samplePoint = new Point(1, 2)
    Cluster sampleCluster = new Cluster(id: 1, centroid: samplePoint, points: [samplePoint])


    def "Test for constructor"() {
        when:
        def cluster = new Cluster(4)

        then:
        cluster.getId() == 4
        cluster.getCentroid() == null
        cluster.getPoints().size() == 0
    }

    def "Test for getter/setter methods"() {
        when:
        def cluster = sampleCluster

        then:
        cluster.getId() == 1
        cluster.getCentroid().getX() == 1
        cluster.getCentroid().getY() == 2
        cluster.getPoints().size() == 1
    }


    def "Test for addPoint"() {
        given:
        def cluster = sampleCluster
        def testPoint = new Point(4, 5)

        when:
        cluster.addPoint(testPoint)

        then:
        cluster.getPoints().size() == 2
    }

    def "Test for clear"() {
        given:
        def cluster = sampleCluster
        def testPoint = new Point(4, 5)

        when:
        cluster.clear()

        then:
        cluster.getPoints().size() == 0
    }

    def "Test for plotCluster"() {
        given:
        def cluster = sampleCluster

        when:
        def result = cluster.plotCluster()

        then:
        result.getId() == 1
        result.getCentroid().getX() == 1
        result.getCentroid().getY() == 2
        result.getPoints().size() == 1
    }

}
