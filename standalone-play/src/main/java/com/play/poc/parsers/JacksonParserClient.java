package com.play.poc.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.play.poc.util.JsonUtils;
import com.play.poc.util.PlayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.*;

public class JacksonParserClient {

    public static void main(String[] args) {
        try{


            PlayUtils playUtils = new PlayUtils();
            //String senzingResponseJson = playUtils.readFileContent("sample-json/senzing-multi-results-response-api.json");
            String senzingResponseJson = playUtils.readFileContent("sample-json/senzing-response-api.json");

            /** Translate response json string to Maps and navigate through json tree and cherry pick the ihrId value.*/
            @SuppressWarnings("unchecked")
            LinkedHashMap<String, Object> apiResponse = JsonUtils.deserializeJson(LinkedHashMap.class, senzingResponseJson);

            String ihrId = optimizedSearchForIhrId(apiResponse);
            System.out.println("ihrId ::"+ihrId);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    private static String searchForIhrId(List<LinkedHashMap<String, Object>> searchResults) {
        String searchedIhrId = null;
        for (LinkedHashMap<String, Object> resultData : searchResults) {
            Integer matchLevelIndicator = (Integer) resultData.get("matchLevel");

            if (matchLevelIndicator.intValue() == 1) {
                List<String> identifierData = (List<String>) resultData.get("identifierData");

                for (String identData : identifierData) {
                    if (identData.contains("OWNER_CHID")) {
                        searchedIhrId = StringUtils.substringAfter(identData, ":").trim();
                        System.out.println("searchedIhrId  :: " + searchedIhrId);
                    }
                }
            }
        }
        return searchedIhrId;
    }

    @SuppressWarnings("unchecked")
    private static String optimizedSearchForIhrId(LinkedHashMap<String, Object> senzingApiResponse) throws Exception {
        String searchedIhrId = null;

        LinkedHashMap<String, Object> dataJson = (LinkedHashMap<String, Object>) senzingApiResponse.get("data");
        List<LinkedHashMap<String, Object>> searchResults = (List<LinkedHashMap<String, Object>>) dataJson.get("searchResults");

        for (LinkedHashMap<String, Object> resultData : searchResults) {
            Integer matchLevelIndicator = (Integer) resultData.get("matchLevel");
            List<LinkedHashMap<String, Object>> recordSummaries = (List<LinkedHashMap<String, Object>>)resultData.get("recordSummaries");
            Integer recordCount = (Integer)recordSummaries.get(0).get("recordCount");

            if (matchLevelIndicator.intValue() == 1 && recordCount.intValue() == 1) {
                List<String> identifierData = (List<String>) resultData.get("identifierData");
                searchedIhrId = StringUtils.substringAfter(identifierData.stream().filter(data -> data.startsWith("OWNER_CHID")).findFirst().get(), ":").trim();
            }
        }

        if(StringUtils.isBlank(searchedIhrId)){
            throw new Exception("Unable to get IhrId for given member");
        }

        return searchedIhrId;
    }

}
