package com.poc.gridfs.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiRequest class used as request contract for service api's consumers.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Data
@NoArgsConstructor
public class ApiRequest {

    @JsonAlias({ "imageName", "fileName", "videoName" })
    private String fileName;
}
