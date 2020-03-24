package com.play.poc.strings;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.util.*;
import java.util.regex.Pattern;

public class SearchTranslation {

    public static void main(String[] args) {
        try {

            String incomingValue = "ADVERSE_REACTION";
            String response = "adverseReactions";

            String[] requestValues = {"ADVERSE_REACTION".toLowerCase(), "CARE_GIVERS".toLowerCase(), "VISIT_HISTORY".toLowerCase(),
                    "PROCEDURE_HISTORY".toLowerCase(), "SERVICE_FACILITY_PROVIDER".toLowerCase(),
                    "HEALTH_MEDICATION".toLowerCase(), "HEALTH_DEVICE".toLowerCase()};


            Set<String> requestKeys = new HashSet<>(Arrays.asList(requestValues));

            String[] responseKeys = {"careTeam", "conditions", "healthStatues", "immunization", "medications",
                    "healthObservations", "procedureHistory", "visitHistory", "adverseReactions",
                    "careGivers", "healthDevices", "serviceProviders"};


            Map<String, List<String>> resultValues = new HashMap<>();
            Map<String, List<String>> responeValues = buildResponses();
            for (Map.Entry<String, List<String>> entry : responeValues.entrySet()) {
                String responseKey = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey());

                boolean validDataClass = isValidDataClass(requestKeys, responseKey);
                System.out.println(">>> responseKey :: " + responseKey + "... validDataClass :: " + validDataClass);

                if (entry.getValue().size() > 0) {
                    if (requestKeys.contains(responseKey)) {
                        resultValues.put(entry.getKey(), entry.getValue());
                    }
                } else {
                    if (validDataClass && entry.getValue().size() == 0) {
                        resultValues.put(entry.getKey(), entry.getValue());
                    }
                }
            }


            System.out.println("Result Values :: " + resultValues.toString());

            boolean isValidX = "HEALTH_MEDICATION".toLowerCase().contains("medications");
            System.out.println("Is Valid X :: "+isValidX);

//           // String givenWord = "SERVICE_FACILITY_PROVIDER".replace("_"," ").toLowerCase();
//            //String gWord = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, givenWord).replace("_", " ").toLowerCase();
//            String inputValueTest = "serviceProviders";
//            //String givenWord = "SERVICE_FACILITY_PROVIDER".toLowerCase();
//            String givenWord = "CARE_GIVERS".toLowerCase();
//            boolean isClassValid = false;
//
//            if(givenWord.equalsIgnoreCase("SERVICE_FACILITY_PROVIDER".toLowerCase())){
//                String[] xTest = inputValueTest.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
//                isClassValid = givenWord.contains(xTest[0]);
//            }else{
//                isClassValid = false;
//            }
//
//            System.out.println("isClassValid == "+isClassValid);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static boolean isValidDataClass(Set<String> requestKeys, String lookupValue) {
        final String computedLookupValue = lookupValue.equalsIgnoreCase("service_providers") ? "service_facility_providers" : lookupValue;


        boolean isRequestedDataClass = requestKeys.stream().anyMatch(computedLookupValue::contains);

        return isRequestedDataClass;
    }



    private static Map<String, List<String>> buildResponses() {
        Map<String, List<String>> respValues = new HashMap<>();
        String[] sampleValues = {"test1", "test2"};
        List<String> tValues = new ArrayList<>(Arrays.asList(sampleValues));
        List<String> noValues = new ArrayList<>();

        respValues.put("careTeam", tValues);
        respValues.put("conditions", noValues);
        respValues.put("immunization", tValues);
        respValues.put("medications", tValues);
        respValues.put("healthObservations", tValues);
        respValues.put("procedureHistory", tValues);
        respValues.put("visitHistory", noValues);
        respValues.put("adverseReactions", noValues);
        respValues.put("careGivers", tValues);
        respValues.put("healthStatues", tValues);
        respValues.put("healthDevices", tValues);
        respValues.put("serviceProviders", noValues);
        return respValues;
    }


    public static LinkedList<String> splitCamelCaseString(String s) {
        LinkedList<String> result = new LinkedList<String>();
        for (String w : s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            result.add(w);
        }
        return result;
    }

//            "ADVERSE_REACTION",
//            "HEALTH_CONDITION",
//            "IMMUNIZATIONS",
//            "HEALTH_DEVICE",
//            "CARE_GIVER",
//            "HEALTH_MEDICATION",
//            "CARE_TEAM",
//            "HEALTH_OBSERVATIONS",
//            "HEALTH_STATUS",
//            "PROCEDURE_HISTORY",
//            "SERVICE_FACILITY_PROVIDER",
//            "VISIT_HISTORY"


    // String splitKey = lookupValue.contains("service_providers")? "service_facility_providers" : lookupValue;
    // System.out.println("Splitted Value :: " + splitKey);

    // boolean isRequestedDataClass = false;

//        for (String requestKey : requestKeys) {
//            if (lookupValue.contains(requestKey)) {
//                isRequestedDataClass = true;
//            }
//        }


    /**
     * Method to validate requested data class.
     *
     * @param dcStrings
     * @param dataClassValue
     * @return boolean
     */
//    private static boolean isValidRequestedDataClass(Set<String> dcStrings, String dataClassValue) {
//        //boolean isRequestedDataClass = false;
//
//        /** Need to keep track if the response results for data classes are empty.*/
//        final String computedDataClassValue = dataClassValue.equalsIgnoreCase("service_providers") ? RecordType.SERVICE_FACILITY_PROVIDER.toString().toLowerCase() : dataClassValue;
//
//        boolean isRequestedDataClass = dcStrings.stream().anyMatch(computedDataClassValue::contains);
//
////        for (String dcValue : dcStrings) {
////            if (dataClassValue.contains(dcValue)) {
////                isRequestedDataClass = true;
////            }
////        }
//        return isRequestedDataClass;
//    }
}
