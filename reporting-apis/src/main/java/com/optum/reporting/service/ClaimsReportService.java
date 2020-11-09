package com.optum.reporting.service;

import com.ee.dynamicmongoquery.MongoQuery;
import com.ee.dynamicmongoquery.MongoQueryParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.optum.reporting.model.ClaimsLag;
import com.optum.reporting.request.ClaimLagRequest;
import com.optum.reporting.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClaimsReportService class used to query mondoDB and format the database result set.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to E&A team. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class ClaimsReportService {
    private static final String DATE_SERVICE_START = "dateOfServiceStart";
    private static final String TOTAL_PAID_AMOUNT = "totalPaidAmt";

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${queryfile.claimlag}")
    private String claimLagQueryPath;

    /**
     * Method to query and get claims lag data.
     *
     * @param claimLagRequest
     * @return List
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<ClaimsLag> claimsLagResults(ClaimLagRequest claimLagRequest) throws Exception {
        /** Get the db object from template. */
        DB mongoDB = mongoTemplate.getMongoDbFactory().getLegacyDb();

        /** Get the mongodb shell query and pass the input parameters to query. */
        String reportQuery = appUtils.readFileContent(claimLagQueryPath);
        reportQuery = StringUtils.replace(reportQuery, "bookYear", claimLagRequest.getBookYear());
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("mgroupId", claimLagRequest.getMemberGroupId());

        /** Parse and execute the mongodb query. */
        MongoQueryParser parser = new MongoQueryParser();
        MongoQuery mongoQuery = parser.parse(reportQuery, queryParams);
        BasicDBList results = mongoQuery.execute(mongoDB);

        /* Compute and build the claim lag report data.*/
        List<ClaimsLag> claimsLagData = buildClaimLagReportData(results);
        return claimsLagData;
    }


    /**
     * Method to compute monthly amount and build the claim lag report data.
     *
     * @param results
     * @return List
     */
    @SuppressWarnings("unchecked")
    private List<ClaimsLag> buildClaimLagReportData(BasicDBList results) {
        Map<String, Double> monthTracking = new HashMap<>();

        /** Compute and Build the mongo query results. */
        for (Object basicDBObj : results) {
            BasicDBObject bdbObj = (BasicDBObject) basicDBObj;
            LinkedHashMap<String, Object> reportData = (LinkedHashMap<String, Object>) bdbObj.toMap();

            /* Get the date of service and total paid amount for each claim. */
            String dateOfService = (String) reportData.get(DATE_SERVICE_START);
            String monthlyAmount = (String) reportData.get(TOTAL_PAID_AMOUNT);

            /* Validate if total paid amount is null or empty default to zero dollar amount. */
            Double tAmount = StringUtils.isNotBlank(monthlyAmount) ? Double.parseDouble(monthlyAmount) : 0.0;
            String month = dateOfService.substring(0, dateOfService.length() - 3);

            /* Group and calculate the total amount paid by monthly. */
            if (monthTracking.containsKey(month)) {
                Double totalAmountPerMonth = monthTracking.get(month);
                totalAmountPerMonth = totalAmountPerMonth + tAmount;
                monthTracking.put(month, totalAmountPerMonth);
            } else {
                monthTracking.put(month, tAmount);
            }
        }

        /* Convert map to list claim lag report data. */
        List<ClaimsLag> claimLagReportData = monthTracking.entrySet()
                .stream()
                .map(mAmount -> new ClaimsLag(mAmount.getKey(), mAmount.getValue()))
                .collect(Collectors.toList());

        return claimLagReportData;
    }

}
