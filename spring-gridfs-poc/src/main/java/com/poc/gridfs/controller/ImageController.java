package com.poc.gridfs.controller;

import com.poc.gridfs.model.ApiRequest;
import com.poc.gridfs.model.Image;
import com.poc.gridfs.service.ImageService;
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
@Api(value = "/gridfspoc", description = "image service api")
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * Method to create image.
     *
     * @param apiRequest
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Creating new image")
    @RequestMapping(value = "/image", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> create(@RequestBody ApiRequest apiRequest) {
        try {
            log.info("Creating  new image: {}", apiRequest.toString());
            Image processedImage = imageService.saveImage(apiRequest.getFileName());
            return new ResponseEntity<>(processedImage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or saving image: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get image by name.
     *
     * @param imageName
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get image by image name")
    @RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getImageByName(@PathVariable String imageName) {
        try {
            log.info("Search image by imageName: {}", imageName);
            String responseMessage = imageService.getImageByName(imageName);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get image by imageName: {}, with exception: {}", imageName, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
