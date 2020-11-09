package com.optum.reporting.service;


import com.ee.dynamicmongoquery.MongoQuery;
import com.ee.dynamicmongoquery.MongoQueryParser;
import com.google.common.collect.Collections2;
import com.google.common.collect.Range;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.optum.reporting.model.ClaimExpenses;
import com.optum.reporting.model.ClaimsLag;
import com.optum.reporting.request.ClaimExpenseRequest;
import com.optum.reporting.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * ClaimsExpensiveService class used to query mondoDB and format the database result set.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to E&A team. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class ClaimExpensesService {
    private static final String TOTAL_PAID_AMOUNT = "totalPaidAmt";
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${queryfile.claimexpensive}")
    private String claimExpensiveQueryPath;

    @Value("${mics.maxrange}")
    private int maximumRange;


    /**
     * Method to query and get claims expensive data.
     *
     * @param claimExpensiveRequest
     * @return List
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public LinkedList<ClaimExpenses> claimExpensesResults(ClaimExpenseRequest claimExpensiveRequest) throws Exception {
        /** Get the db object from template. */
        DB mongoDB = mongoTemplate.getMongoDbFactory().getLegacyDb();

        /** Get the mongodb shell query and pass the input parameters to query. */
        String reportQuery = appUtils.readFileContent(claimExpensiveQueryPath);
        reportQuery = StringUtils.replace(reportQuery, "serviceYear", claimExpensiveRequest.getBookYear());
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("mgroupId", claimExpensiveRequest.getMemberGroupId());

        /** Parse and execute the mongodb query. */
        MongoQueryParser parser = new MongoQueryParser();
        MongoQuery mongoQuery = parser.parse(reportQuery, queryParams);
        BasicDBList results = mongoQuery.execute(mongoDB);

        /* Compute and build the claim lag report data.*/
        LinkedList<ClaimExpenses> claimExpensesReportData = buildClaimLagReportData(results);
        return claimExpensesReportData;
    }

    /**
     * Method to compute claims expenses report data.
     *
     * @param results
     * @return Map
     */
    @SuppressWarnings("unchecked")
    private LinkedList<ClaimExpenses> buildClaimLagReportData(BasicDBList results) {
        LinkedList<ClaimExpenses> claimExpensesData = new LinkedList<>();
        List<Double> claimAmounts = new ArrayList<>();

        /** Compute and Build the mongo query results. */
        for (Object basicDBObj : results) {
            BasicDBObject bdbObj = (BasicDBObject) basicDBObj;
            LinkedHashMap<String, Object> ceData = (LinkedHashMap<String, Object>) bdbObj.toMap();

            /* Collect all claims amounts.*/
            Double paidClaimAmount = Double.parseDouble((String) ceData.get(TOTAL_PAID_AMOUNT));
            claimAmounts.add(paidClaimAmount);
        }

        /* Build the header claim expense information and claims expense input information. */
        Double allClaimsTotalPaidAmount = claimAmounts.stream().mapToDouble(Double::doubleValue).sum();
        ClaimExpenses headerClaimExpenses = buildHeaderClaimExpenses(allClaimsTotalPaidAmount, claimAmounts);
        Map<String, Object> claimsExpenseInput = buildClaimsExpenseInput(allClaimsTotalPaidAmount, claimAmounts);
        claimExpensesData.add(headerClaimExpenses);

        /* Build the claim amount range information. */
        for (int iter = 1; iter <= maximumRange; iter = iter + 100) {
            double minValue = (double) iter - 1;
            double maxValue = (double) iter + 100 - 1;
            ClaimExpenses claimExpenses = buildClaimExpense(minValue, maxValue, claimsExpenseInput);
            claimExpensesData.add(claimExpenses);
        }

        return claimExpensesData;
    }


    /**
     * Method to build claim expense report data.
     *
     * @param minValue
     * @param maxValue
     * @param claimExpensesInput
     * @return ClaimExpenses
     */
    @SuppressWarnings("unchecked")
    private ClaimExpenses buildClaimExpense(double minValue, double maxValue, Map<String, Object> claimExpensesInput) {
        ClaimExpenses claimExpenses = new ClaimExpenses();

        /* Get the claims expense input information. */
        String rangeKey = "$".concat(String.valueOf(minValue).replace(".0", "")).concat("-").concat("$").concat(String.valueOf(maxValue).replace(".0", ""));
        List<Double> inputValues = (List<Double>) claimExpensesInput.get("claimAmounts");
        int totalClaims = (Integer) claimExpensesInput.get("totalClaims");
        double totalAmount = (Double) claimExpensesInput.get("totalAmount");

        /* Calculate the number of claims and total amount with in given range. */
        Collection<Double> claimPriceData = Collections2.filter(inputValues, Range.closed(minValue, maxValue));
        int numberOfClaims = claimPriceData.size();
        Double totalPaidAmount = claimPriceData.stream().mapToDouble(Double::doubleValue).sum();
        String perClaimants = decimalFormat.format(((double) numberOfClaims / (double) totalClaims) * 100).concat("%");
        String perPayments = decimalFormat.format((totalPaidAmount / totalAmount) * 100).concat("%");

        /* Populate the claim expenses information. */
        claimExpenses.setRange(rangeKey);
        claimExpenses.setNumberOfClaimants(String.valueOf(numberOfClaims));
        claimExpenses.setTotalPayments(String.valueOf(totalPaidAmount));
        claimExpenses.setPercentageClaimants(perClaimants);
        claimExpenses.setPercentagePayments(perPayments);
        return claimExpenses;
    }

    /**
     * Method to build claims expense input information for further computing process.
     *
     * @param totalAmount
     * @param inputValues
     * @return Map
     */
    private Map<String, Object> buildClaimsExpenseInput(Double totalAmount, List<Double> inputValues) {
        Map<String, Object> claimExpensesInput = new HashMap<>();
        claimExpensesInput.put("claimAmounts", inputValues);
        claimExpensesInput.put("totalAmount", totalAmount);
        claimExpensesInput.put("totalClaims", inputValues.size());
        return claimExpensesInput;
    }


    /**
     * Method to build the header for claim expense.
     *
     * @param totalAmount
     * @param inputValues
     * @return ClaimExpense
     */
    private ClaimExpenses buildHeaderClaimExpenses(Double totalAmount, List<Double> inputValues) {
        ClaimExpenses headerClaimExpenses = new ClaimExpenses();
        headerClaimExpenses.setRange("$0-$".concat(String.valueOf(maximumRange)));
        headerClaimExpenses.setPercentagePayments("100%");
        headerClaimExpenses.setPercentageClaimants("100%");
        headerClaimExpenses.setTotalPayments(String.valueOf(totalAmount));
        headerClaimExpenses.setNumberOfClaimants(String.valueOf(inputValues.size()));
        return headerClaimExpenses;
    }
}
