package com.poc.gridfs.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * AppUtils class used to handle all reusable methods and constants with in application.
 *
 * @author Ravi Reddy.
 * @CopyRight (C) All rights reserved to UHG Inc. It's Illegal to reproduce this code.
 */
public class AppUtils {
    public static final String OUTPUT_DIR = "output";
    public static final String IMAGE_RESPONSE_MSG = "Searched image is available in output folder of the project";
    public static final String VIDEO_RESPONSE_MSG = "Searched video is available in output folder of the project";
    public static final String BIG_FILE_RESPONSE_MSG = "Searched big file is available in output folder of the project";

    /**
     * Method to create files for search queries.
     *
     * @throws Exception
     */
    public static void searchResultsFile(String directory, String fileName) throws Exception {
        File file = getFileInfo(directory, fileName);

        /** Delete existing file and create new file.*/
        file.delete();
        File theDirectory = new File(directory);
        if (!theDirectory.exists()){
            theDirectory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        file.createNewFile();
    }

    /**
     * Method to get the file information.
     *
     * @return File
     */
    public static File getFileInfo(String directory, String fileName){
        Path path = Paths.get(directory);
        File file = new File(path.getFileName().toString().concat(File.separator).concat(fileName));
        return file;
    }
}
