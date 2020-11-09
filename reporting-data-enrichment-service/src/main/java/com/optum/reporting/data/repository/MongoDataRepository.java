package com.optum.reporting.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * MongoDataRepository class used to ingest the data into given collections.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to UHC E&A Team. It's Illegal to reproduce this code.
 */
@Repository
public class MongoDataRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public <T> void saveData(String collectionName, T dataEntity) {
        mongoOperations.save(dataEntity, collectionName);
    }

}
