package com.optum.hub.controller;

import com.optum.hub.model.ClaimEvent;
import com.optum.hub.service.EventProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/enrichment", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/enrichment", description = "enrichment service")
@Slf4j
public class EnrichmentController {

    @Autowired
    EventProducer eventProducer;

    /**
     * Method for health check.
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "", notes = "Health check end point")
    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity healthCheck() {
        try {
            log.info("Health check endpoint");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Health check is down");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to publish event into kafka topic.
     *
     * @param claimEvent
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Publishing claim event")
    @RequestMapping(value = "/publish", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity publishEvent(@RequestBody ClaimEvent claimEvent) {
        try {
            log.info("Publishing claim event into kafka topic: {}", claimEvent.toString());
            eventProducer.publish(claimEvent);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues publishing message into kafka topic: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
