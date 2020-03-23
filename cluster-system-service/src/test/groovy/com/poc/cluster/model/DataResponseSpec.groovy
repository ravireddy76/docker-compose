package com.poc.cluster.model

import spock.lang.Specification

class DataResponseSpec extends Specification {
    Point samplePoint = new Point(1, 2)
    Cluster sampleCluster = new Cluster(id: 1, centroid: samplePoint, points: [samplePoint])

    DataResponse dataResponse = new DataResponse(iteration: 1, centroidDistance: 1.234567, cluster: sampleCluster)


    def "Test for getter/setter"() {
        when:
        def sampleDataResponse = dataResponse

        then:
        sampleDataResponse.getIteration() == 1
        sampleDataResponse.getCentroidDistance() == 1.234567
        sampleDataResponse.getCluster().getId() == 1
        sampleDataResponse.getCluster().getCentroid().getX() == 1
        sampleDataResponse.getCluster().getCentroid().getY() == 2
        sampleDataResponse.getCluster().getPoints().size() == 1
    }

    def "Test for toString"() {
        when:
        DataResponse sampleDataResponse = dataResponse
        def result = sampleDataResponse.toString()

        then:
        result.contains("DataResponse(iteration=1, centroidDistance=1.234567, cluster=Cluster(id=1, centroid=Point(x=1.0, y=2.0, clusterNumber=0), points=[Point(x=1.0, y=2.0, clusterNumber=0)]))")
    }

}
