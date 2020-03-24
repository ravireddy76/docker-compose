package com.play.poc.strings;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ZeroPaddingClient {

    public static void main(String[] args) {
        try {

//            String inputValueX = "00000637367123";
//            inputValueX = (inputValueX.length() == 7 || inputValueX.length() == 8) ? StringUtils.leftPad(inputValueX, 9, "0") : inputValueX;
//            System.out.println("Result :: " + inputValueX);
//
//
//            String searchId = "0000012345678999";
//            paddingSubscriberId(searchId);
//
//            String inputValue = "0000123456";
//            stripZeros(inputValue);

            String[] inputValues = {"123456789", "11123456789", "0012345678999", "0123456789", "0123456", "000123456"};

            for (String inputValue :inputValues) {
                ImmutableMap<String, String> computedSubscriberIds = validateSubscriberId(inputValue);

                System.out.println("Input Value :: " + inputValue);
                System.out.println("========================================================================");
                System.out.println("Subscriber Id :: " + computedSubscriberIds.get("subscriberId"));
                System.out.println("Subscriber Pad ID :: " + computedSubscriberIds.get("subscriberPadId"));
                System.out.println(System.lineSeparator());

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void paddingSubscriberId(String searchId) {
        String subscriberId = StringUtils.isNotBlank(searchId) ? StringUtils.leftPad(searchId, 11, "0") : searchId;
        System.out.println("SubscriberId value :: " + subscriberId);
    }


    private static void stripZeros(String inputValue) {
        String computedValue = StringUtils.stripStart(inputValue, "0");
        System.out.println("Computed Value :: "+computedValue);
    }

    private static ImmutableMap<String, String> validateSubscriberId(String inputValue) {
        String subscriberId;
        String subscriberPadId;

        boolean isStartsWithZero = StringUtils.startsWith(inputValue, "0");

        if (isStartsWithZero) {
            subscriberId = StringUtils.stripStart(inputValue, "0");
            subscriberPadId = inputValue;

        } else {
            String computedSubscriberId = StringUtils.isNotBlank(inputValue) ? StringUtils.leftPad(inputValue, 11, "0") : inputValue;
            subscriberId = inputValue;
            subscriberPadId = computedSubscriberId;
        }

        ImmutableMap<String, String> computedSubscriberIds = ImmutableMap.of("subscriberId", subscriberId, "subscriberPadId", subscriberPadId);
        return computedSubscriberIds;

    }

}
