package com.poc.cluster.model

import spock.lang.Specification

class DataResultSpec extends Specification {
    Point samplePoint = new Point(1, 2)
    Cluster sampleCluster = new Cluster(id: 1, centroid: samplePoint, points: [samplePoint])

    DataResult dataResult = new DataResult(iteration: 1, centroidDistance: 1.2, clusters: [sampleCluster])

    def "Test for getter/setter"() {
        when:
        def sampleDataResult =  dataResult

        then:
        sampleDataResult.getIteration() == 1
        sampleDataResult.getCentroidDistance() == 1.2
        sampleDataResult.getClusters().size() == 1
    }

}
