package com.play.poc.strings;

import com.google.common.base.CaseFormat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CaseStylesClient {

    public static void main(String[] args) {
        try {

//            String inputValue = "presenceStateTerm";
//            String translatedKey = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, inputValue);
//            System.out.println("TranslatedKey :: "+translatedKey);

            lowerCaseWithDot("uhgrd.HELIUM");
            lowerCaseWithDot("UHGRD.helium");
            lowerCaseWithDot("UHGRD.HELIUM");
            lowerCaseWithDot("UHGrd.hELiuM");

            String[] words = {"ace", "boom", "crew", "dog", "eon"};
            Set<String> mySet = new HashSet<String>(Arrays.asList(words));
            System.out.println("Set Values :: "+mySet);

            System.out.println("Test Contains :: "+mySet.contains("dog"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static void lowerCaseWithDot(String inputValue) {
        String result = inputValue.toLowerCase();
        System.out.println(">>> Result :: "+result);
        System.out.println(System.lineSeparator());
    }
}
