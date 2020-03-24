package com.play.poc.strings;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApostropheClient {

    public static void main(String[] args) {
        try {

            //String s = "test's";

            String testJson = sampleJson();

//            Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:'[a-zA-Z]+)*$");
//            Matcher matcher = pattern.matcher(testJson);
//            if (matcher.matches()) {
//                System.out.println("true");
//            } else {
//                System.out.println("false");
//            }

            testJson = new JSONObject(testJson).toString();
            System.out.println(testJson);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static String sampleJson() {
        String testJson = "{\n" +
                "  \"correlationId\": \"123aa\",\n" +
                "  \"language\": \"EN\",\n" +
                "  \"mbrID\": {\n" +
                "    \"big5\": {\n" +
                "      \"firstName\": \"firstnametest\",\n" +
                "      \"lastName\": \"lastnametest's\",\n" +
                "      \"dateOfBirth\": \"1954/04/05\",\n" +
                "      \"policyNumber\": \"\",\n" +
                "      \"searchId\": \"70296148\"\n" +
                "    },\n" +
                "    \"idType\": \"RALLYID\",\n" +
                "    \"idValue\": \"UTD 10\"\n" +
                "  },\n" +
                "  \"dataClasses\": [\n" +
                "    \"ADVERSE_REACTION\",\n" +
                "    \"HEALTH_CONDITION\",\n" +
                "    \"HEALTH_DEVICE\",\n" +
                "    \"CARE_GIVER\",\n" +
                "    \"HEALTH_MEDICATION\",\n" +
                "    \"CARE_TEAM\",\n" +
                "    \"HEALTH_OBSERVATIONS\",\n" +
                "    \"HEALTH_STATUS\",\n" +
                "    \"PROCEDURE_HISTORY\",\n" +
                "    \"SERVICE_FACILITY_PROVIDER\",\n" +
                "    \"VISIT_HISTORY\",\n" +
                "    \"IMMUNIZATIONS\"\n" +
                "  ]\n" +
                "}";

        return testJson;
    }
}
