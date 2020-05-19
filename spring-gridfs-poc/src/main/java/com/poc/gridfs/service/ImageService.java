package com.poc.gridfs.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.poc.gridfs.configuration.VehicleConfig;
import com.poc.gridfs.model.Image;
import com.poc.gridfs.model.StorageContent;
import com.poc.gridfs.model.Vehicle;
import com.poc.gridfs.repository.GridFsRepository;
import com.poc.gridfs.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;


/**
 * ImageService class used to handle CRUD operations and save into mongodb.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class ImageService {

    @Value("${collection.imageColl}")
    private String imageCollection;

    @Autowired
    private GridFsRepository gridFsRepository;

    @Autowired
    private VehicleConfig vehicleConfig;

    /**
     * Method to save(or create) image in a given collection name in mongoDB.
     *
     * @param imageName
     * @return Image
     */
    public Image saveImage(String imageName) {
        String image2Save = imageName.concat(".png");

        /** Read and Create metadata information for image to save. */
        DBObject metaData = buildImageMetaData(imageName);
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream("pictures/".concat(image2Save));
        StorageContent storageContent = StorageContent.builder().collectionName(imageCollection).fileName(imageName)
                .contentType("image/png").metaData(metaData).inputStream(imageStream).build();

        /** Save the image in mongoDB for given collection name.*/
        String objectId = gridFsRepository.save(storageContent);

        /** Build response information. */
        Image image = Image.builder().id(objectId).title(image2Save).build();
        return image;
    }

    /**
     * Method to read the image by name.
     *
     * @param imageName
     * @return
     * @throws Exception
     */
    public String getImageByName(String imageName) throws Exception {
        GridFSDBFile imageFile = gridFsRepository.fetchByName(imageCollection, imageName);
        File searchResultFile = AppUtils.getFileInfo(AppUtils.OUTPUT_DIR, imageFile.getFilename().concat(".png"));
        imageFile.writeTo(searchResultFile.getPath());
        String responseMessage = AppUtils.IMAGE_RESPONSE_MSG;
        return responseMessage;
    }

    /**
     * Method to build image metadata information.
     *
     * @param imageName
     */
    private DBObject buildImageMetaData(String imageName) {
        DBObject metaData = new BasicDBObject();

        /** Build image metadata for given image name. */
        for (Vehicle vehicle : vehicleConfig.getVehicles()) {
            if (vehicle.getBrand().equalsIgnoreCase(imageName)) {
                metaData.put("brand", vehicle.getBrand());
                metaData.put("model", vehicle.getModel());
                metaData.put("description", vehicle.getDescription());

            }
        }
        return metaData;
    }

}
