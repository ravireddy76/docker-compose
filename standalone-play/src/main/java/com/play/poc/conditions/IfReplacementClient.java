package com.play.poc.conditions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IfReplacementClient {

    public static void main(String[] args){
        try{

            List<String> testList = new ArrayList<>(Arrays.asList("T1", "T2"));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private static String validateList(List<String> tList) throws Exception {
        String emptyExMessage = "Unable to find ihr identifier (IhrId) from senzing response for given member";
        String morethanOneExMessage = "Found multiple ihr identifiers: " + tList;
        String result = "";

//        String result = CollectionUtils.isEmpty(tList) ? "" : (tList.size() > 1) ? "" : Iterables.getOnlyElement(tList);
//
//        if(StringUtils.isBlank(result)){
//            throw new Exception(emptyExMessage);
//        }

        return result;
    }

    private static void throwException(String exceptionMessage) throws Exception {
        throw new Exception(exceptionMessage);
    }
}
