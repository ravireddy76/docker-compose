package com.play.poc.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class String2ValuesClient {

    public static void main(String[] args) {
        try {
            String inputValue = "2.0";
            int result = Float.valueOf(inputValue).intValue();

            //Integer.parse(inputValue);

            //System.out.println("Result :: "+result);

            int version = 2;
            String consumer = "testXy";
            String[] configClients = {"test1", "ravi", "testX"};
            Set<String> clients = new HashSet<>(Arrays.asList(configClients));

            String computedValue = (version >= 2 && clients.contains(consumer)) ? "b50" : "ihr2";
            System.out.println("Computed Value :: " + computedValue);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
