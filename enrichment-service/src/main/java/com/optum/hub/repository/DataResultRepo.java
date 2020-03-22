package com.optum.hub.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 *  DataResultRepo class used to query member claims data by query criteria.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Repository
public class DataResultRepo {

    @Autowired
    MongoOperations mongoOperations;

    /**
     * Method to save data results into given collection.
     *
     * @param collectionName
     * @param entity
     * @param <T>
     */
    public <T> void saveDataResults(String collectionName, T entity) {
        mongoOperations.save(entity, collectionName);
    }
}
