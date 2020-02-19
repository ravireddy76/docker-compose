package com.mn.poc.service;

import com.mn.poc.model.User;
import com.mn.poc.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * UserService class acts like delegate between controller and dao layer to handle business cases.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Slf4j
@Singleton
public class UserService {

    @Inject
    UserRepo userRepo;

    /**
     * Method to create user.
     *
     * @param user
     * @return User
     */
    public User createUser(User user) {
        userRepo.saveUser(user);
        return user;
    }

    /**
     * Method to update user.
     *
     * @param user
     * @return User
     */
    public User updateUser(User user) {
        userRepo.updateUser(user, user.getUserId());
        return user;
    }

    /**
     * Method to delete user.
     *
     * @param userId
     */
    public void deleteUser(String userId) {
        userRepo.deleteUser(userId);
    }

    /**
     * Method to get all users.
     *
     * @return List
     */
    public List<Document> getAllUsers() throws Exception {
        return userRepo.findAllUsers();
    }


    /**
     * Method to get user by userId.
     *
     * @param userId
     * @return User
     */
    public User getUserById(String userId) throws Exception {
        User searchedUser = userRepo.findUserById(userId);
        return searchedUser;
    }

}
