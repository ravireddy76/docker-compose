package com.play.poc.strings;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Stream;

public class StringSearchClient {

    //private static final String MATCH_KEY = "+NAME+DOB+GENDER+MEDICARE_BENF_ID";
   // private static final String MATCH_KEY = "-DOB+GENDER+MEDICARE_BENF_ID";
   // private static final String MATCH_KEY = "+PNAME+DOB+GENDER+MEDICARE_BENF_ID";
    // private static final String MATCH_KEY = "+PNAME-DOB+GENDER+MEDICARE_BENF_ID";
    private static final String MATCH_KEY = "-PNAME+DOB+GENDER+MEDICARE_BENF_ID";

    public static void main(String[] args) {
        try{
            String[] matchKeys = {"+DOB","+NAME","+PNAME"};
            //String[] matchKeys = {"+DOB+NAME","+DOB+PNAME"};

//            boolean isKeyMatched =  Arrays.asList(matchKeys).stream().anyMatch(MATCH_KEY::contains);
//            System.out.println("Result :: "+isKeyMatched);


            boolean isKeyMatched = MATCH_KEY.contains(matchKeys[0]) && (MATCH_KEY.contains(matchKeys[1]) || MATCH_KEY.contains(matchKeys[2]));

                    Stream.of("+PNAME", "+NAME").anyMatch(MATCH_KEY::equalsIgnoreCase);
                    //Stream.of("+PNAME", "+NAME").anyMatch(MATCH_KEY::equalsIgnoreCase);
                      //StringUtils.equalsAnyIgnoreCase(MATCH_KEY, matchKeys[1], matchKeys[2]);
                      //MATCH_KEY.contains(matchKeys[0]); &&
                      // StringUtils.equalsAnyIgnoreCase(MATCH_KEY, matchKeys[1], matchKeys[2]);
             System.out.println("Result :: "+isKeyMatched);

                      //MATCH_KEY.contains(matchKeys[0]) && (MATCH_KEY.contains(matchKeys[1]) || MATCH_KEY.contains(matchKeys[2]);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
