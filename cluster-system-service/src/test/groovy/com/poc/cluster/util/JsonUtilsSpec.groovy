package com.poc.cluster.util

import com.poc.cluster.model.DataRequest
import com.poc.cluster.model.DataResult
import spock.lang.Specification

class JsonUtilsSpec extends Specification {
    DataRequest dataRequest = new DataRequest(numClusters: 4, numPoints: 20, minCoordinate: 0, maxCoordinate: 20)

    def "Test for serialization"() {
        when:
        def result = JsonUtils.serializeJson(dataRequest)

        then:
        result.contains("{\"numClusters\":4,\"numPoints\":20,\"minCoordinate\":0,\"maxCoordinate\":20}")
    }

    def "Test for deserializeJson"() {
        given:
        def sampleJson = "{\"numClusters\":4,\"numPoints\":20,\"minCoordinate\":0,\"maxCoordinate\":20}"

        when:
        def result = JsonUtils.deserializeJson(DataRequest.class, sampleJson)

        then:
        result.minCoordinate == 0
        result.maxCoordinate == 20
        result.numClusters == 4
        result.numPoints == 20
    }

    def "Test for deserializeObjectsCollection"() {
        given:
        def sampleJson = "[{\"numClusters\":4,\"numPoints\":20,\"minCoordinate\":0,\"maxCoordinate\":20}]"

        when:
        def result = JsonUtils.deserializeObjectsCollection(DataRequest.class, sampleJson)

        then:
        result.size() == 1
        result[0].minCoordinate == 0
        result[0].maxCoordinate == 20
        result[0].numClusters == 4
        result[0].numPoints == 20
    }

}
