package com.poc.demo.dao;

import com.poc.demo.model.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserDao class used class used to handle database (read, write, create and delete) activities.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Repository
public class UserDao {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserDao.class);
    private Map<String, User> users = new HashMap<>();

    /**
     * Method to save the user.
     *
     * @param user
     */
    public void saveUser(User user) {
        log.info("User: {}", user.toString());
        users.put(user.getUserId(), user);
    }

    /**
     * Method to update the existing User.
     *
     * @param user
     * @param userId
     */
    public void updateUser(User user, String userId) {
        log.info("User to update by userId: {}", userId);
        users.put(userId, user);
    }

    /**
     * Method to delete user by userId.
     *
     * @param userId
     */
    public void deleteUser(String userId) {
        log.info("Deleting user by userId: {}", userId);
        users.remove(userId);
    }

    /**
     * Method to find all users.
     *
     * @return List
     */
    public List<User> findAllUsers() {
        List<User> allUsers = users.values().stream().collect(Collectors.toList());
        return allUsers;
    }

    /**
     * Method to find User by userId.
     *
     * @param userId
     * @return User
     */
    public User findUserById(String userId) {
        log.info("UserId to search: {}", userId);
        User searchedUser = users.get(userId);
        return searchedUser;
    }

}
