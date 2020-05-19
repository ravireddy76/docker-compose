package com.poc.gridfs.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;

/**
 * Image class used for response contract for image api service.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@Builder
public class Image {

    private String id;
    private String title;
    private Binary image;
}
