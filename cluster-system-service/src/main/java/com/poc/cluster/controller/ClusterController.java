package com.poc.cluster.controller;

import com.poc.cluster.model.DataRequest;
import com.poc.cluster.model.DataResponse;
import com.poc.cluster.service.ClusterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cspoc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/cspoc", description = "API to handle cluster operations")
@Slf4j
public class ClusterController {

    @Autowired
    ClusterService clusterService;


    /**
     * Method to create data point and clusters information.
     *
     * @param dataRequest
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Create data points and number of clusters")
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody DataRequest dataRequest) {
        try {
            log.info("Creating new data points with clusters information: {}", dataRequest.toString());
            clusterService.createAndComputeClusters(dataRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create data points and number of clusters: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Method to get cluster information by iteration id and cluster id.
     *
     * @param iterationId
     * @param clusterId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get cluster information by iteration id and cluster id")
    @RequestMapping(value = "/iteration/{iterationId}/cluster/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse> getClusterById(@PathVariable int iterationId, @PathVariable int clusterId) {
        try {
            log.info("Search cluster by iterationId: {}, clusterId: {}", iterationId, clusterId);
            DataResponse dataResponse = clusterService.getClusterById(iterationId, clusterId);
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get cluster information by iterationId: {}, clusterId: {}, with exception: {}", iterationId, clusterId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}
