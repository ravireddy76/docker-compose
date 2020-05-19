package com.poc.gridfs.controller;

import com.poc.gridfs.model.ApiRequest;
import com.poc.gridfs.model.BigFile;
import com.poc.gridfs.service.FileService;
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
@Api(value = "/gridfspoc", description = "file service api")
@Slf4j
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * Method to create file.
     *
     * @param apiRequest
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Creating new file")
    @RequestMapping(value = "/bigfile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigFile> create(@RequestBody ApiRequest apiRequest) {
        try {
            log.info("Creating  new bigfile: {}", apiRequest.toString());
            BigFile processedFile = fileService.saveFile(apiRequest.getFileName());
            return new ResponseEntity<>(processedFile, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or saving bigfile: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get file by name.
     *
     * @param fileName
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get file by file name")
    @RequestMapping(value = "/bigfile/{fileName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFileByName(@PathVariable String fileName) {
        try {
            log.info("Search file by fileName: {}", fileName);
            String responseMessage = fileService.getFileByName(fileName);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get file by fileName: {}, with exception: {}", fileName, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
