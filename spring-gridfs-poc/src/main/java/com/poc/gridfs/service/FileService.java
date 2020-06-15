package com.poc.gridfs.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.poc.gridfs.model.BigFile;
import com.poc.gridfs.model.StorageContent;
import com.poc.gridfs.repository.GridFsRepository;
import com.poc.gridfs.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * FileService class used to handle large file size CRUD operations and save into mongodb.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class FileService {

    @Value("${collection.fileColl}")
    private String fileCollection;

    @Autowired
    private GridFsRepository gridFsRepository;

    /**
     * Method to save(or create) file in a given collection name in mongoDB.
     *
     * @param fileName
     * @return Image
     */
    public BigFile saveFile(String fileName) {
        String file2Save = fileName.concat(".json");

        /** Read and create metadata information for file to save. */
        DBObject metaData = buildFileMetaData(fileName);
        InputStream fileStream = getClass().getClassLoader().getResourceAsStream("files/".concat(file2Save));
        StorageContent storageContent = StorageContent.builder().collectionName(fileCollection).fileName(fileName)
                .contentType("text/json").metaData(metaData).inputStream(fileStream).build();

        /** Save the image in mongoDB for given collection name.*/
        String objectId = gridFsRepository.save(storageContent);

        /** Build the file response. */
        BigFile bigFile = BigFile.builder().id(objectId).size((String) metaData.get("size"))
                .description((String) metaData.get("description")).build();

        return bigFile;
    }


    /**
     * Method to read the file by name.
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public String getFileByName(String fileName) throws Exception {
        GridFSDBFile bigFile = gridFsRepository.fetchByName(fileCollection, fileName);
        File searchResultFile = AppUtils.getFileInfo(AppUtils.OUTPUT_DIR, bigFile.getFilename().concat(".json"));
        bigFile.writeTo(searchResultFile.getPath());
        String responseMessage = AppUtils.BIG_FILE_RESPONSE_MSG;
        return responseMessage;
    }


    /**
     * Method to build file metadata information.
     *
     * @param fileName
     */
    private DBObject buildFileMetaData(String fileName) {
        String computedFileName = StringUtils.replace(fileName, ".json", "");
        DBObject metaData = new BasicDBObject();
        metaData.put("size", StringUtils.split(fileName, "-")[0]);
        metaData.put("description", computedFileName.concat(" JSON file"));
        return metaData;
    }

}
