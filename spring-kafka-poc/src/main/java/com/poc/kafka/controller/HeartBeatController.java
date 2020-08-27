package com.poc.kafka.controller;

import com.poc.kafka.model.Message;
import com.poc.kafka.service.EventPublisher;
import com.poc.kafka.util.JsonUtils;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/dataenrichment", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/dataenrichment", description = "Data Enrichment Service")
@Slf4j
public class HeartBeatController {

    @Autowired
    private EventPublisher eventPublisher;

    /**
     * Method to get file by name.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Heart Beat")
    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> heartBeat() {
        try {
            log.info("Inside HeartBeat");

            /* Building as response message object.*/
            Map<String, String> response = new HashMap<>();
            response.put("time", new Date().toString());
            response.put("message", "I'm alive");

            /* Converting response object to JSON string. */
            String responseMessage = JsonUtils.serializeJson(response);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get heart beat  with exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to publish event into kafka topic.
     *
     * @param message
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Publishing claim event")
    @RequestMapping(value = "/publish", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity publishEvent(@RequestBody Message message) {
        try {
            log.info("Publishing message event into kafka topic: {}", message.toString());
            eventPublisher.publish(message);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues publishing message into kafka topic: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
