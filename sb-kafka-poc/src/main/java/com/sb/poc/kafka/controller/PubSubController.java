package com.sb.poc.kafka.controller;

import com.sb.poc.kafka.model.Message;
import com.sb.poc.kafka.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/sbkafkapoc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/sbkafkapoc", description = "Heart Beat API")
@Slf4j
public class PubSubController {

    @Autowired
    private EventService eventService;

    @Value("${topics.test}")
    private String testTopic;

    /**
     * Method to publish event into kafka topic.
     *
     * @param message
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Publishing event")
    @RequestMapping(value = "/publish", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity publishEvent(@RequestBody Message message) {
        try {
            log.info("Publishing message event into kafka topic: {}", message.toString());
            eventService.publish(testTopic, message);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues publishing message into kafka {} topic: {}", testTopic, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
