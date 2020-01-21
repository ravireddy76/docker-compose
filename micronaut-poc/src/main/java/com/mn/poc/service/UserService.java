package com.mn.poc.service;


import com.mn.poc.dao.UserDao;
import com.mn.poc.model.User;
import lombok.extern.slf4j.Slf4j;

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
    UserDao userDao;

    /**
     * Method to create user.
     *
     * @param user
     * @return User
     */
    public User createUser(User user) {
        userDao.saveUser(user);
        return user;
    }

    /**
     * Method to update user.
     *
     * @param user
     * @return User
     */
    public User updateUser(User user) {
        userDao.updateUser(user, user.getUserId());
        return user;
    }

    /**
     * Method to delete user.
     *
     * @param userId
     */
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    /**
     * Method to get all users.
     *
     * @return List
     */
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }


    /**
     * Method to get user by userId.
     *
     * @param userId
     * @return User
     */
    public User getUserById(String userId) {
        User searchedUser = userDao.findUserById(userId);
        return searchedUser;
    }

}
