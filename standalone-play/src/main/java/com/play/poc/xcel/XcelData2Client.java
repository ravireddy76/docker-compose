package com.play.poc.xcel;

import com.poiji.bind.Poiji;

import java.io.File;
import java.util.List;

public class XcelData2Client {
    private static String INPUT_FILE = "/Users/rredd16/IntelliJ-Playgrounds/standalone-play/src/main/resources/hxCommissions_Transactions_Paid.xlsx";

    public static void main(String[] args){
        try {

            List<XcelData1> dataRecords = Poiji.fromExcel(new File(INPUT_FILE), XcelData1.class);

            System.out.println("DataRecords :: "+dataRecords.size());

            for (XcelData1 data : dataRecords) {
                System.out.println("Data :: "+data.toString());
                System.out.println(System.lineSeparator());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
