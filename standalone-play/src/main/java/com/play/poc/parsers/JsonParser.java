package com.play.poc.parsers;

import com.play.poc.util.PlayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;



public class JsonParser {

    public static void main(String[] args) {

        try{

            PlayUtils playUtils = new PlayUtils();
            String senzingResponseJson = playUtils.readFileContent("sample-json/senzing-response-api.json");

            JSONObject jsonObj = new JSONObject(senzingResponseJson);
            JSONArray responseData = jsonObj.getJSONObject("data").getJSONArray("searchResults");

            for (int iter = 0 ; iter < responseData.length(); iter++) {
                JSONObject obj = responseData.getJSONObject(iter);

                int matchLevel = obj.getInt("matchLevel");
                if(matchLevel == 1){
                    JSONArray ownerChid = obj.getJSONArray("identifierData");

                    for (int j = 0; j < ownerChid.length(); j++) {
                        String chidObj = ownerChid.getString(j);

                        if(chidObj.contains("OWNER_CHID:")){
                            String ihrId = StringUtils.substringAfter(chidObj, ":").trim();
                            System.out.println("ihrId :: "+ihrId);
                        }
                    }

                }
                System.out.println("Match Level :: "+matchLevel);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
