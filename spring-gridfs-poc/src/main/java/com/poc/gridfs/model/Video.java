package com.poc.gridfs.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;

/**
 * Video class used for response contract for video api service.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@Builder
@Document
public class Video {

    private String id;
    private String title;
    private InputStream stream;
}
