package com.poc.gridfs.model;

import lombok.Builder;
import lombok.Data;

/**
 * BigFile class used for response contract for file api service.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@Builder
public class BigFile {

    private String id;
    private String size;
    private String description;
}
