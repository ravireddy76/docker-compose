package com.play.poc.files;

import com.play.poc.util.PlayUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVDiffClient {
    public static void main(String[] args) {

        try{
            List<String> deltaResults = new LinkedList();
            PlayUtils playUtils = new PlayUtils();
            List<String> originalData = playUtils.readFileData("sample-csv/original-data.csv");
            List<String> incomingData  = playUtils.readFileData("sample-csv/incoming-data.csv");

            for(String inData : incomingData) {
                if(!originalData.contains(inData)) {
                     deltaResults.add(inData);
                }
            }

            System.out.println("Delta Results....."+deltaResults.size());
            for (String delta : deltaResults) {
                System.out.println(delta);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
