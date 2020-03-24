package com.play.poc.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayUtils {

    public String readFileContent(String fileName) throws Exception {
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());

        Stream<String> lines = Files.lines(path);
        String fileContent = lines.collect(Collectors.joining("\n"));
        return fileContent;
    }


    public List<String> readFileData(String fileName) throws Exception {
        List<String> records = new LinkedList();
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());

        Stream<String> lines = Files.lines(path);
        records = lines.collect(Collectors.toList());
        return records;
    }


//    public String getFilePath(String fileName){
//        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
//        return path;
//    }
}
