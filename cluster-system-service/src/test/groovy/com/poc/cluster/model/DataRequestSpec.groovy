package com.poc.cluster.model

import spock.lang.Specification

class DataRequestSpec extends Specification {
    DataRequest dataRequest = new DataRequest(numClusters: 4, numPoints: 20, minCoordinate: 0, maxCoordinate: 20)

    def "Test for getter/setter"() {
        when:
        def sampleDataRequest = dataRequest

        then:
        sampleDataRequest.getNumClusters() == 4
        sampleDataRequest.getNumPoints() == 20
        sampleDataRequest.getMinCoordinate() == 0
        sampleDataRequest.getMaxCoordinate() == 20
    }

    def "Test for toString"() {
        when:
        DataRequest sampleDataRequest = dataRequest
        def result = sampleDataRequest.toString()

        then:
        result.contains("DataRequest(numClusters=4, numPoints=20, minCoordinate=0, maxCoordinate=20)")
    }

}
