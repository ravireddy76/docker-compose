package com.play.poc.dates;

import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateRangeClient {
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    private static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String[] DATE_PATTERNS = {ISO_DATE_FORMAT, ISO_DATETIME_FORMAT};


    public static void main(String[] args) {
        try {

            Date startDate = DateUtils.parseDateStrictly("2019-04-27 03:21:22", DATE_PATTERNS);
            Date endDate = DateUtils.parseDateStrictly("2019-11-27 03:21:22", DATE_PATTERNS);
            Date inputDate = DateUtils.parseDateStrictly("2019-01-26 11:21:22", DATE_PATTERNS);

            boolean isDateWithInRange = isDateInBetweenRange(startDate, endDate, inputDate);
            System.out.println("IS Date With in Range :: "+isDateWithInRange);


            DateTimeFormatter parser = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy-MM-dd HH:mm:ss]");
            LocalDate startDate8 = LocalDate.parse("2019-04-27 03:21:22", parser);
            LocalDate endDate8 = LocalDate.parse("2019-11-27 03:21:22", parser);
            LocalDate inputDate8 = LocalDate.parse("2019-01-26 11:21:22", parser);

            boolean isDateWithInRange8 = isDateInBetweenRange8(startDate8, endDate8, inputDate8);
            System.out.println("IS Date With in Range  8:: "+isDateWithInRange8);


        } catch (Exception ex) {

        }
    }




//    private static boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
//        return testDate.getTime() >= startDate.getTime() &&
//                testDate.getTime() <= endDate.getTime();
//    }

    private static boolean isDateInBetweenRange(final Date startDate, final Date endDate, final Date inputDate){
        return !(inputDate.before(startDate) || inputDate.after(endDate));
    }

    private static boolean isDateInBetweenRange8(final LocalDate startDate, final  LocalDate endDate, final  LocalDate inputDate){
        return !(inputDate.isBefore(startDate) || inputDate.isAfter(endDate));
    }
}
