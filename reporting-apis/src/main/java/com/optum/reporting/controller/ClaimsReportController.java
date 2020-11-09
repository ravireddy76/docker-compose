package com.optum.reporting.controller;

import com.optum.reporting.model.ClaimExpenses;
import com.optum.reporting.model.ClaimsLag;
import com.optum.reporting.request.ClaimExpenseRequest;
import com.optum.reporting.request.ClaimLagRequest;
import com.optum.reporting.service.ClaimExpensesService;
import com.optum.reporting.service.ClaimsReportService;
import com.optum.reporting.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/reporting-data/claims", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/reporting-data/claims", description = "Claims Reporting Apis")
@Slf4j
public class ClaimsReportController {

    @Autowired
    private ClaimsReportService claimsReportService;

    @Autowired
    private ClaimExpensesService claimExpensesService;

    /**
     * Method to get claims lag report data.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get the claim lag report")
    @RequestMapping(value = "/claimlag", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClaimsLag>> getClaimLagReport(
            @ApiParam(value = "Claim Lag Request", required = true) @RequestBody ClaimLagRequest claimLagRequest) {
        try {
            log.info("Claim lag request: {}", JsonUtils.serializeJson(claimLagRequest));

            List<ClaimsLag> claimLagData = claimsReportService.claimsLagResults(claimLagRequest);
            return new ResponseEntity<>(claimLagData, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to getClaimLagReport with exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get claim expenses report data.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get the claim expenses report")
    @RequestMapping(value = "/claimexpenses", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinkedList<ClaimExpenses>> getClaimExpensesReport(
            @ApiParam(value = "Claim Expenses Request", required = true) @RequestBody ClaimExpenseRequest claimExpenseRequest) {
        try {
            log.info("Claim expenses request: {}", JsonUtils.serializeJson(claimExpenseRequest));

            LinkedList<ClaimExpenses> claimExpensesData = claimExpensesService.claimExpensesResults(claimExpenseRequest);
            return new ResponseEntity<>(claimExpensesData, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to getClaimExpensesReport with exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
