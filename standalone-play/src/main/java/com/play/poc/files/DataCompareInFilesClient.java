package com.play.poc.files;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.play.poc.util.PlayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCompareInFilesClient {

    public static void main(String[] args) {
        try {

            PlayUtils playUtils = new PlayUtils();

            List<String> originalData = playUtils.readFileData("sample-csv/original-data.csv");
            List<String> incomingData  = playUtils.readFileData("sample-csv/incoming-data.csv");

            System.out.println("originalData >> "+originalData.size());

            Patch patch = DiffUtils.diff(originalData, incomingData);

            //List deltaResults = DiffUtils.patch(originalData, patch);

            //List<String> deltaResults = patch.getDeltas();

            System.out.println(">>> DeltaResults :: "+patch.getDeltas().size());


            for (Object delta: patch.getDeltas()) {
                System.out.println(delta);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
