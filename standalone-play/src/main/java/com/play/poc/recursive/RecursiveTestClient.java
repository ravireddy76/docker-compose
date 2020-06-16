package com.play.poc.recursive;

import java.util.*;

public class RecursiveTestClient {

    public static void main(String args[]) {
        try {
            process();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void process() {
        Map<String, Integer> resultMap = new HashMap<>();
        int dataCounter = 0;
        Map<String, Integer> results = getFirstCall(resultMap, dataCounter);
        System.out.println("results :: "+results.toString());
    }

    private static Map<String, Integer> getFirstCall(Map<String, Integer> resultMap, int dataCounter) {
        int randomSize = randomDataSize();
        System.out.println(">> Random Size :: "+randomSize+",,  Data Counter :: "+dataCounter);

        if(randomSize < 1000) {
            resultMap.put("Counter".concat(String.valueOf(dataCounter)), randomSize);
            return resultMap;
        }else{
            resultMap.put("Counter".concat(String.valueOf(dataCounter)), randomSize);
            dataCounter++;
            return getFirstCall(resultMap, dataCounter);
        }

//        resultMap.put("Counter".concat(String.valueOf(counter)), randomSize);
//        return getFirstCall();
    }

    private static int randomDataSize() {
        List<Integer> givenList = Arrays.asList(1000, 1000, 3);
        Random rand = new Random();
        int randomSize = givenList.get(rand.nextInt(givenList.size()));
        return randomSize;
    }
}
