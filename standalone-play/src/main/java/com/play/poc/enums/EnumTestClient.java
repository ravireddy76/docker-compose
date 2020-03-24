package com.play.poc.enums;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnumTestClient {

    private static final List<String> referenceValues = requestValues("Present, Not Present, Past Occurrence, Planned Occurrence");

    public static void main(String[] args) {
        try {

            List<String> requestValues = requestValues("Presents, Not Presents");
            List<Boolean> isAnyPresentStates = new ArrayList<>();

            for (String requestValue : requestValues) {
                boolean isValuePresent = isInReference(requestValue);

                if (!isValuePresent) {
                    isAnyPresentStates.add(new Boolean(isValuePresent));
                }
            }

            System.out.println("Values present in Enum Size :: " + isAnyPresentStates.size());
            if (isAnyPresentStates.size() == requestValues.size()) {
                System.out.println("Throw exception -> there is no present state");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<String> requestValues(String requestValue) {
        return Arrays.asList(requestValue.split("\\s*,\\s*"));
    }

    public static boolean isInReference(String requestValue) {
        boolean isValidValue = referenceValues.contains(requestValue);
        return isValidValue;
    }
}
