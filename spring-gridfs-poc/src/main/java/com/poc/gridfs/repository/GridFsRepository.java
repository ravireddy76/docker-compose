package com.poc.gridfs.repository;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.poc.gridfs.model.StorageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;


/**
 * GridFsRepository class used to save and query images/videos/files.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Repository
public class GridFsRepository {

    @Autowired
    private MongoClient mongoClient;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    private DB db;

    @PostConstruct
    public void init(){
        db = mongoClient.getDB(mongoDatabase);
    }

    /**
     * Method to save the file in a given collection.
     *
     * @param storageContent
     */
    public String save(StorageContent storageContent) {
        GridFS gridFS = new GridFS(db, storageContent.getCollectionName());
        GridFSInputFile gfsFile = gridFS.createFile(storageContent.getInputStream());
        gfsFile.setFilename(storageContent.getFileName());
        gfsFile.setMetaData(storageContent.getMetaData());
        gfsFile.setContentType(storageContent.getContentType());
        gfsFile.save();
        String imageId = gfsFile.getId().toString();
        return imageId;
    }

    /**
     * Method to fetch or query file by name.
     *
     * @param collectionName
     * @param fileName
     * @return GridFSDBFile
     */
    public GridFSDBFile fetchByName(String collectionName, String fileName) {
        GridFS gridfs = new GridFS(db, collectionName);
        GridFSDBFile gridFSDBFile = gridfs.findOne(fileName);
        return gridFSDBFile;
    }

    /**
     * Method to delete file in a given collection and file name.
     *
     * @param collectionName
     * @param fileName
     */
    public void delete(String collectionName, String fileName) {
        GridFS gridfs = new GridFS(db, collectionName);
        gridfs.remove(gridfs.findOne(fileName));
    }

}
