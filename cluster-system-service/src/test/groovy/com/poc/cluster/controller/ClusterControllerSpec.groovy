package com.poc.cluster.controller

import com.poc.cluster.model.Cluster
import com.poc.cluster.model.DataRequest
import com.poc.cluster.model.DataResponse
import com.poc.cluster.model.Point
import com.poc.cluster.service.ClusterService
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ClusterControllerSpec extends Specification {
    DataRequest dataRequest = new DataRequest(numClusters: 4, numPoints: 20, minCoordinate: 0, maxCoordinate: 20)
    ClusterService mockClusterService = Mock(ClusterService)
    ClusterController clusterController = new ClusterController(clusterService: mockClusterService)


    def "Test for create data points and cluster information: #scenario"() {
        when:
        def response = clusterController.create(dataRequest)

        then:
        if (isException) {
            1 * mockClusterService.createAndComputeClusters(_ as DataRequest) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockClusterService.createAndComputeClusters(_ as DataRequest)
        }

        /** Validate the response for each scenario.*/
        response != null
        response.statusCode.reasonPhrase == statusReason

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }


    def "Test for getClusterById: #scenario"() {
        when:
        def response = clusterController.getClusterById(1, 2)

        then:
        if (isException) {
            1 * mockClusterService.getClusterById(_, _) >> { throw new Exception("Invalid Request") }
        } else {
            1 * mockClusterService.getClusterById(_, _) >> { sampleDataResponse() }
        }

        /** Validate the response for each scenario.*/
        response != null
        response.statusCode.reasonPhrase == statusReason

        where:
        scenario         | isException | statusReason
        "Happy Path"     | false       | "OK"
        "Exception Path" | true        | "Bad Request"
    }

    private void sampleDataResponse() {
        Point samplePoint = new Point(1, 2)
        Cluster sampleCluster = new Cluster(id: 1, centroid: samplePoint, points: [samplePoint])
        DataResponse dataResponse = new DataResponse(iteration: 1, centroidDistance: 1.234567, cluster: sampleCluster)
        dataResponse
    }

}
