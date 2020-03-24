package com.play.poc.strings;

import org.apache.commons.lang3.RandomUtils;

public class RandomNumberGenerator {

    public static void main(String[] args){
        try{

            int testVal = RandomUtils.nextInt();
            System.out.println("Test Value :: "+testVal);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
