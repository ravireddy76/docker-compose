package com.mn.poc.controller;

import com.mn.poc.model.User;
import com.mn.poc.service.UserService;
import io.micronaut.context.annotation.Context;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@Context
@Controller(value = "/demopoc")
public class UserController {
    @Inject
    UserService userService;


    /**
     * Method to create user.
     *
     * @param user
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @Post(uri = "/user", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public User create(@Body User user) {
        log.info("Creating new user: {}", user.toString());
        User registeredUser = userService.createUser(user);
        return registeredUser;
    }

    /**
     * Method to update user.
     *
     * @param user
     * @return ResponseEntity
     */
    @Put(uri = "/user", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public User update(@Body User user) {
        log.info("Updating existing user: {}", user.toString());
        User updatedUser = userService.updateUser(user);
        return updatedUser;
    }

    /**
     * Method to delete user.
     *
     * @param userId
     * @return ResponseEntity
     */
    @Delete(uri = "/user/{userId}")
    public String delete(@PathVariable("userId") String userId) {
        log.info("Deleting existing user by id: {}", userId);
        userService.deleteUser(userId);
        return "User information is deleted by id :" + userId;
    }

    /**
     * Method to get user by userId.
     *
     * @param userId
     * @return ResponseEntity
     */
    @Get(uri = "/user/{userId}", produces = MediaType.APPLICATION_JSON)
    public User getUserById(@PathVariable String userId) throws Exception {
        log.info("Search user by userId : " + userId);
        User searchedUser = userService.getUserById(userId);
        return searchedUser;
    }

    /**
     * Method to get all users.
     *
     * @return ResponseEntity
     */
    @Get(uri = "/user/all", produces = MediaType.APPLICATION_JSON)
    public List<Document> getAllUsers() throws Exception {
        log.info("Search all users");
        List<Document> users = userService.getAllUsers();
        return users;
    }

}
