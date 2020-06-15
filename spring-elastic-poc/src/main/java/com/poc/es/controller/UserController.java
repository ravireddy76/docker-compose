package com.poc.es.controller;


import com.poc.es.model.User;
import com.poc.es.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import java.util.List;


@Controller
@RequestMapping(value = "/demopoc", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/demopoc", description = "API to handle user CRUD operations")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    /**
     * Method to create user.
     *
     * @param user
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Create new user")
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            log.info("Creating new user: {}", user.toString());
            User registeredUser = userService.create(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or register user: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to update user.
     *
     * @param user
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Updating existing user")
    @RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            log.info("Updating existing user: {}", user.toString());
            User updatedUser = userService.update(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to updating existing user: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to delete user.
     *
     * @param userId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Delete existing user")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> delete(
            @ApiParam(value = "userId", required = true) @PathVariable("userId") String userId) {
        try {
            log.info("Deleting existing user by id: {}", userId);
            //userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to delete user by id: {}, with exception: {}", userId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get user by userId.
     *
     * @param userId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get user by user id")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(
            @ApiParam(value = "userId", required = true) @PathVariable String userId) {
        try {
            log.info("Search user by userId : " + userId);
            User searchedUser = userService.getUserById(userId);
            return new ResponseEntity<>(searchedUser, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get user by userId: {}, with exception: {}", userId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Method to get all users by zipcode.
     *
     * @param zipCode
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get all users by zipcode")
    @RequestMapping(value = "/user/{zipCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> getAllUsersByZipCode(@PathVariable String zipCode) {
        try {
            log.info("Search all users by zipcode");
            List<User> users = userService.getAllUsersByZipcode(zipCode);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get all users by zipcode exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
