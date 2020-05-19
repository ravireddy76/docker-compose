package com.poc.gridfs.model;

import com.mongodb.DBObject;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

/**
 * StorageContent class used to hold the gridfs file properties to save data into mongodb.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@Builder
public class StorageContent {

    private String collectionName;
    private String fileName;
    private String contentType;
    private DBObject metaData;
    private InputStream inputStream;
}
