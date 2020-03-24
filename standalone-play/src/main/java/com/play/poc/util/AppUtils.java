package com.play.poc.util;


import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.routines.RegexValidator;

import java.time.LocalDate;
import java.util.Date;

public class AppUtils {
    public static final String CRD_FILTER_CLASSES = "healthDevices,medications,immunizations,healthStatuses,procedureHistory,visitHistory";
    public static final String[] DATE_PATTERNS = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
    public static final String[] DATE_VALUE_REGEXS = {"[0-9]{4}\\-[0-9]{1,2}", "[0-9]{4}\\--[0-9]{1,2}",
            "[0-9]{4}\\/[0-9]{1,2}\\/[0-9]{1,2}", "[0-9]{4}\\-[0-9]{1,2}\\/[0-9]{1,2}", "[0-9]{4}\\/[0-9]{1,2}\\-[0-9]{1,2}", "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}",
            "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}", "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}\\:[0-9]{1,2}",
            "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}\\::[0-9]{1,2}", "[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}\\:[0-9]{1,2}\\:[0-9]{1,2}"};

    public static final String CR_DATE_EX_MESSAGE = "Date parsing issues for clinical relevant date: {}";
    public static final String CR_START_DATE_EX_MESSAGE = "Date parsing issues for clinical relevant start date: ";
    public static final String CR_END_DATE_EX_MESSAGE = "Date parsing issues for clinical relevant end date: ";

    public static final String CLINICALLY_RELEVANT_DATE = "clinicallyRelevantDate";
    public static final String CLINICALLY_RELEVANT_START_DATE = "clinicallyRelevantStartDate";
    public static final String CLINICALLY_RELEVANT_END_DATE = "clinicallyRelevantEndDate";
    public static final String PRESENCE_STATE = "presenceState";
    public static final String PRESENCE_STATE_TERM = "presenceStateTerm";


    /**
     * Method to parse input date string to date object.
     *
     * @param filterDate
     * @param exceptionMessage
     * @return Date
     */
    public static Date parseInputDate(String filterDate, String exceptionMessage) throws Exception {
        Date date;

        try {
            date = DateUtils.parseDateStrictly(filterDate, AppUtils.DATE_PATTERNS);
        } catch (Exception ex) {
            throw new Exception(exceptionMessage.concat(ex.getMessage()));
        }

        return date;
    }

    /**
     * Method to validate the input value is tex or date.
     *
     * @param inputValue
     * @return boolean
     */
    public static boolean isDateValue(String inputValue) {
        RegexValidator validator = new RegexValidator(AppUtils.DATE_VALUE_REGEXS, false);
        boolean isDate = validator.isValid(inputValue);
        return isDate;
    }

    /**
     * Method to get today date in yyyy-MM-dd.
     *
     * @return String
     */
    public static Date todayDate() throws Exception {
        return parseInputDate(LocalDate.now().toString(), CR_END_DATE_EX_MESSAGE);
    }


    /**
     * Method to validate given date with in given start & end date.
     *
     * @param startDate
     * @param endDate
     * @param inputDate
     * @return boolean
     */
    public static boolean isDateInBetween(final Date startDate, final Date endDate, final Date inputDate) {
        return !(inputDate.before(startDate) || inputDate.after(endDate));
    }

}
