package com.poc.gridfs.controller;

import com.poc.gridfs.model.ApiRequest;
import com.poc.gridfs.model.BigFile;
import com.poc.gridfs.model.Video;
import com.poc.gridfs.service.VideoService;
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
@RequestMapping(value = "/gridfspoc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/gridfspoc", description = "video service api")
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;


    /**
     * Method to create video.
     *
     * @param apiRequest
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Creating new video")
    @RequestMapping(value = "/video", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Video> create(@RequestBody ApiRequest apiRequest) {
        try {
            log.info("Creating new video: {}", apiRequest.toString());
            Video processedVideo = videoService.saveVideo(apiRequest.getFileName());
            return new ResponseEntity<>(processedVideo, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or saving video: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get video by name.
     *
     * @param videoName
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get video by name")
    @RequestMapping(value = "/video/{videoName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getVideoByName(@PathVariable String videoName) {
        try {
            log.info("Search video by name: {}", videoName);
            String responseMessage = videoService.getVideoByName(videoName);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get video by name: {}, with exception: {}", videoName, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
