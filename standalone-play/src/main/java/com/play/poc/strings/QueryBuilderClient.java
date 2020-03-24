package com.play.poc.strings;

import com.play.poc.util.PlayUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilderClient {
    public static void main(String[] args){
        try{
            List<String> ochids  = buildOchids();

            String ihrQueryBase = "Delete from ii.\"IHRId\" where \"ihrIdentifier\"=";
            String payloadQueryBase = "Delete from data.\"Payload\" where \"ihrIdentifier\"=";

            List<String> ihrQueries = new ArrayList<>();
            List<String> payloadQueries = new ArrayList<>();

            for (String ochid: ochids) {
               String ihrQuery = ihrQueryBase+"'"+ochid+"';";
               String payloadQuery = payloadQueryBase+"'"+ochid+"';";

                ihrQueries.add(ihrQuery);
                payloadQueries.add(payloadQuery);
            }

            StringBuilder queryInfoBuilder = new StringBuilder();

            System.out.println("Queries Information....");
            for (String ihrQry : ihrQueries) {
                queryInfoBuilder.append(ihrQry).append("\n");

                //System.out.println(ihrQry);
            }

            System.out.println(System.lineSeparator());

            queryInfoBuilder.append(System.lineSeparator());
            for (String payloadQry : payloadQueries) {
                queryInfoBuilder.append(payloadQry).append("\n");
               // System.out.println(payloadQry);
            }


//            PlayUtils playUtils = new PlayUtils();
//            Files.write(playUtils.getFilePath("/queryBuilder.txt"),queryInfoBuilder.toString().getBytes());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


//    Delete from ii."IHRId" where "ihrIdentifier"='1234665';
//
//    Delete from data."Payload" where "ihrIdentifier"='UID001021700';
    private static List<String> buildOchids(){
        String[] ochids = {
                "ACT103607245",
                "ACT104446732",
                "ACT107703612",
                "ACT114516724",
                "ACT141379281",
                "ACT25394752",
                "ACT46189631",
                "ACT49160732",
                "ACT50595301",
                "ACT51384371",
                "ACT63015074",
                "ACT65131390",
                "ACT69475858",
                "ACT71288698",
                "ACT72629123",
                "ACT73163606",
                "ACT73369558",
                "ACT81077178",
                "ACT83652448",
                "ACT84169207",
                "ACT89071081",
                "ACT91484088",
                "ACT97864032",
                "ACT98157773"};

        List<String> ochidList = Arrays.asList(ochids);
        return ochidList;
    }
}
