package com.play.poc.lists;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ListFilterClient {

    public static void main(String[] args) {
        try {

            //listFiltering();

            String inputValue = "E";
            List<String> refList = value2List();
            boolean isValueAvailable = refList.contains(inputValue);

            System.out.println("IS Value Available :: "+isValueAvailable);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static  List<String> value2List(){
        String alpha = "A,B, C,D";
        List<String> result = Arrays.asList(alpha.split("\\s*,\\s*"));
        System.out.println("Result :: "+result);
        return result;
    }

    private static void listFiltering(){
        List<String> testData = Arrays.asList("MEDICARE_BENF_ID: 2WW7HA0NR79", "SUBSCRIBER_ID: 700029859370","OWNER_CHID: ACT33716240","HCID: 950318829","SSN: XXXXXXXXX");

        String ihrId = StringUtils.substringAfter(testData.stream().filter(data -> data.startsWith("OWNER_CHID")).findFirst().get(), ":").trim();
        System.out.println("ihrID == "+ihrId);
    }
}
