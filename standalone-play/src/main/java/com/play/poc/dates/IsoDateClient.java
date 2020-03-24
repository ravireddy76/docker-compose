package com.play.poc.dates;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class IsoDateClient {
    public static final String[] DATE_PATTERNS = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss'Z'"};

    public static void main(String[] args) {
        try {

            String inputDate = "2013-09-29T18:46:19Z";

//            String generatedDate = generateIsoDateWithTimeZone(inputDate);
//            System.out.println("generated Date : "+generatedDate);

            Date date = DateUtils.parseDateStrictly(inputDate, DATE_PATTERNS);
            boolean isValidValue = Objects.isNull(date) ? false: true;
            System.out.println("Is Valida Date :: "+isValidValue);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String generateIsoDateWithTimeZone(String inputDate) throws Exception {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateValue = df.parse(inputDate).toString();
        return dateValue;
    }
}
