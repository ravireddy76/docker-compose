package com.poc.es.service;


import com.poc.es.model.User;
import com.poc.es.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService class used to handle CRUD operations and save user information into elastic search.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to create user.
     *
     * @param user
     * @return Customer
     */
    public User create(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    /**
     * Method to update user.
     *
     * @param user
     * @return Customer
     */
    public User update(User user) {
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    /**
     * Method to get user by id.
     *
     * @param userId
     * @return User
     */
    public User getUserById(String userId) {
        User user = userRepository.findByUserId(userId).get(0);
        return user;
    }

    /**
     * Method to get all users by zipcode.
     *
     * @param zipCode
     * @return List
     */
    public List<User> getAllUsersByZipcode(String zipCode) {
        List<User> searchedUsers = userRepository.findByZipCode(zipCode);
        return searchedUsers;
    }

}
