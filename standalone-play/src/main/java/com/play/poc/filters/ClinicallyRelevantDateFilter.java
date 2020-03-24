package com.play.poc.filters;


import com.play.poc.util.AppUtils;
import org.apache.commons.lang3.time.DateUtils;


public class ClinicallyRelevantDateFilter implements DataFilter{

    public ClinicallyRelevantDateFilter(String startDate, String endDate) throws Exception {
        System.out.println("Inside the ClinicallyRelevantDateFilter with start date :: "+startDate+", and end date :: "+endDate);

        try {

            DateUtils.parseDateStrictly(startDate, AppUtils.DATE_PATTERNS);
            DateUtils.parseDateStrictly(endDate, AppUtils.DATE_PATTERNS);
         } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public boolean filter(com.fasterxml.jackson.databind.JsonNode dataNode) {
        return false;
    }
}
