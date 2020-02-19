package com.mn.poc.repo;


import com.mn.poc.model.User;
import com.mn.poc.util.MongoTxClient;
import io.micronaut.data.annotation.Repository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserRepo class used class used to handle database (read, write, create and delete) activities.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Repository
public class UserRepo {
    @Inject
    private MongoTxClient mongoTxClient;

    /**
     * Method to save the user.
     *
     * @param user
     */
    public void saveUser(User user) {
        log.info("User: {}", user.toString());
        mongoTxClient.saveOrUpdate(user);
    }

    /**
     * Method to update the existing User.
     *
     * @param user
     * @param userId
     */
    public void updateUser(User user, String userId) {
        log.info("User to update by userId: {}", userId);
        mongoTxClient.saveOrUpdate(user);
    }

    /**
     * Method to delete user by userId.
     *
     * @param userId
     */
    public void deleteUser(String userId) {
        log.info("Deleting user by userId: {}", userId);
        //users.remove(userId);
    }

    /**
     * Method to find all users.
     *
     * @return List
     */
    public List<Document> findAllUsers() throws Exception {
        List<Document> allUsers = mongoTxClient.findAllUsers().blockingGet();
        return allUsers;
    }

    /**
     * Method to find User by userId.
     *
     * @param userId
     * @return User
     */
    public User findUserById(String userId) throws Exception {
        log.info("UserId to search: {}", userId);
        User searchedUser =  mongoTxClient.findByUserId(userId);
        return searchedUser;
    }

}
