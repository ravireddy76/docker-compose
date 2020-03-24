package com.play.poc.parsers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.play.poc.util.JsonUtils;

import java.util.Map;

public class SingleQuoteParserClient {

    public static void main(String[] args) {
        try {
            String testJson = sampleJson();
            Map<String, Object> deserializeJson = JsonUtils.deserializeJson(Map.class, testJson);

            String formattedJson = JsonUtils.serializeJson(deserializeJson);
            System.out.println("Formatted Json :: ");
            System.out.println(formattedJson);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static String sampleJson() {
        String jsonValue = "{\n" +
                "  \"dataClasses\": {\n" +
                "    \"conditions\": [\n" +
                "      {\n" +
                "        \"relatedObservations\": [],\n" +
                "        \"clinicallyRelevantDate\": \"\",\n" +
                "        \"lastUpdateDate\": \"2019/07/05\",\n" +
                "        \"concept\": {\n" +
                "          \"icd10cmCode\": \"F41.8\",\n" +
                "          \"sourceVocabulary\": \"ICD-10 Diagnosi's\",\n" +
                "          \"sourceVocabularyCode\": \"F41.8\",\n" +
                "          \"ihrId\": \"CDN416\",\n" +
                "          \"ihrLaymanTerm\": \"Anxiet'y\",\n" +
                "          \"ihrTerm\": \"Anxiety Disorder\"\n" +
                "        },\n" +
                "        \"onsetDate\": \"\",\n" +
                "        \"sourceClaimIds\": [\n" +
                "          \"920191587352800\",\n" +
                "          \"7864404779\",\n" +
                "          \"7862104895\",\n" +
                "          \"919191711672900\",\n" +
                "          \"7888472617\"\n" +
                "        ],\n" +
                "        \"relatedConditions\": [],\n" +
                "        \"relatedCareTeam\": [],\n" +
                "        \"sensitivityClasses\": [\n" +
                "          \"Mental/Behavioral Health\"\n" +
                "        ],\n" +
                "        \"dataSources\": [\n" +
                "          \"System Interface D'ata Acquisition's Method\"\n" +
                "        ],\n" +
                "        \"objectId\": 34230407072770,\n" +
                "        \"startDate\": \"2019/04/20\",\n" +
                "        \"status\": {\n" +
                "          \"sourceVocabulary\": \"\",\n" +
                "          \"sourceVocabularyCode\": \"\",\n" +
                "          \"ihrId\": \"QLR16721\",\n" +
                "          \"ihrLaymanTerm\": \"Active\",\n" +
                "          \"ihrTerm\": \"Active\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        return jsonValue;
    }

}
