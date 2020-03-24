package com.play.poc.dates;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DateValidationClient {
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    private static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ISO_DATETIME_ZONE_FORMAT = "yyyy-MM-ddTHH:mm:ssZ";

//    private static final String DATE_REGEX = "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}";
//    private static final String DATE_TIME_REGEX = "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}\\:[0-9]{1,2}\\:[0-9]{1,2}";

    private static final String[] DATE_PATTERNS = {ISO_DATE_FORMAT, ISO_DATETIME_FORMAT, ISO_DATETIME_ZONE_FORMAT};
    private static final String[] DATE_REGEXS = {"[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}", "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}",
            "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}\\:[0-9]{1,2}","[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}\\:[0-9]{1,2}\\:[0-9]{1,2}"};

    public static void main(String[] args){
        try{


            //String inputValue = "2019-12-17 03:21:22";
            String inputValue = "TestX";
            System.out.println("inputDate ::"+inputValue);


           //isValueIsDate(inputValue);
            boolean isValidDate = isFilterValueIsDate(inputValue);
            System.out.println("Is ValidDate :: "+isValidDate);
            System.out.println(System.lineSeparator());

            LocalDateTime ldt = LocalDateTime.now();


            System.out.println(DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
            System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));

            String todayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
            System.out.println(ldt);
            System.out.println("Todays Date : "+todayDate);

            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("LocalDate = " + localDate.toString());
            System.out.println("LocalDate -> Date = " + date);




        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private static void isValueIsDate(String inputValue) throws Exception {
        boolean isValidDate = false;

        if(StringUtils.isNotBlank(inputValue)){
            boolean isDateValue = isInputIsDate(inputValue);
            if(isDateValue){
               isValidDate = dateValidator(inputValue);
            }
        }

        if(!isValidDate){
            throw new Exception("Input value is not a valid date");
        }
    }


    private static boolean isInputIsDate(String inputValue) {
        RegexValidator validator = new RegexValidator(DATE_REGEXS, false);
        boolean isDate = validator.isValid(inputValue);

        System.out.println(">>> IS Input value is Date: "+isDate);
        return isDate;
    }

    private static boolean dateValidator(String inputDate){
        //boolean isValidDate = (StringUtils.isNotBlank(inputDate) &&  GenericValidator.isDate(inputDate, ISO_DATETIME_FORMAT, true));

        boolean isValidDate = GenericValidator.isDate(inputDate, ISO_DATETIME_FORMAT, true);
        String logMessage = isValidDate ? "Valid date" : "Invalid date";

        System.out.println(">>> Is Valid Date :: "+isValidDate);
        System.out.println("LogMessage :: "+logMessage);

        return isValidDate;
    }


    private static boolean isFilterValueIsDate(String inputValue) {
        boolean isValidDate = false;

        RegexValidator validator = new RegexValidator(DATE_REGEXS, false);
        boolean isDate = validator.isValid(inputValue);
        System.out.println("is Date :: "+isDate);

        if(isDate) {
            try{
                Date date = DateUtils.parseDateStrictly(inputValue, DATE_PATTERNS);
                isValidDate = Objects.isNull(date) ? false : true;
            }catch(Exception ex) {
                isValidDate = false;
            }
        }

        return isValidDate;
    }

}
