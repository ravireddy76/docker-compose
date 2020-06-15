package com.poc.gridfs.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.poc.gridfs.model.StorageContent;
import com.poc.gridfs.model.Video;
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
 * VideoService class used to handle CRUD operations and save into mongodb.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class VideoService {

    @Value("${collection.videoColl}")
    private String videoCollection;

    @Autowired
    private GridFsRepository gridFsRepository;

    /**
     * Method to save(or create) file in a given collection name in mongoDB.
     *
     * @param fileName
     * @return Image
     */
    public Video saveVideo(String fileName) {
        String video2Save = fileName.concat(".mp4");

        /** Read and create metadata information for file to save. */
        DBObject metaData = buildFileMetaData(fileName);
        InputStream fileStream = getClass().getClassLoader().getResourceAsStream("videos/".concat(video2Save));
        StorageContent storageContent = StorageContent.builder().collectionName(videoCollection).fileName(fileName)
                .contentType("video/mp4").metaData(metaData).inputStream(fileStream).build();

        /** Save the image in mongoDB for given collection name.*/
        String objectId = gridFsRepository.save(storageContent);

        /** Build the video response. */
        Video video = Video.builder().id(objectId).title(video2Save).build();
        return video;
    }


    /**
     * Method to read the video by name.
     *
     * @param videoName
     * @return
     * @throws Exception
     */
    public String getVideoByName(String videoName) throws Exception {
        GridFSDBFile bigFile = gridFsRepository.fetchByName(videoCollection, videoName);
        File searchResultFile = AppUtils.getFileInfo(AppUtils.OUTPUT_DIR, bigFile.getFilename().concat(".mp4"));
        bigFile.writeTo(searchResultFile.getPath());
        String responseMessage = AppUtils.VIDEO_RESPONSE_MSG;
        return responseMessage;
    }


    /**
     * Method to build file metadata information.
     *
     * @param fileName
     */
    private DBObject buildFileMetaData(String fileName) {
        String computedFileName = StringUtils.replace(fileName, ".mp4", "");
        DBObject metaData = new BasicDBObject();
        metaData.put("size", StringUtils.split(fileName, "-")[1]);
        metaData.put("description", computedFileName.concat(" mp4 video file"));
        return metaData;
    }

}
