package com.poc.cluster.service

import com.poc.cluster.model.DataRequest
import spock.lang.Specification

class ClusterServiceSpec extends Specification {
    DataRequest sampleDataRequest = new DataRequest(numClusters: 4, numPoints: 20, minCoordinate: 0, maxCoordinate: 20)
    ClusterService clusterService = new ClusterService()

    def "Test for createAndComputeClusters"() {
        expect:
        clusterService.createAndComputeClusters(sampleDataRequest)
    }

    def "Test for getClusterById"() {
        when:
        clusterService.createAndComputeClusters(sampleDataRequest)
        def result = clusterService.getClusterById(1, 2)

        then:
        result
        result.iteration == 1
        result.cluster.points.size() > 1
    }

}
