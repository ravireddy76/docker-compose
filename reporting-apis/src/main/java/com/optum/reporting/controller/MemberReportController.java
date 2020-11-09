package com.optum.reporting.controller;


import com.optum.reporting.model.MemberDetailTracker;
import com.optum.reporting.model.MemberSubscriber;
import com.optum.reporting.request.MemberDetailsRequest;
import com.optum.reporting.request.MemberSubscriberRequest;
import com.optum.reporting.service.MemberReportDetailsService;
import com.optum.reporting.service.MemberReportService;
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

import java.util.List;

@Controller
@RequestMapping(value = "/reporting-data/member", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/reporting-data/member", description = "Member Reporting Apis")
@Slf4j
public class MemberReportController {

    @Autowired
    private MemberReportService memberReportService;

    @Autowired
    private MemberReportDetailsService memberReportDetailsService;

    /**
     * Method to get member subscribers report data.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get the member subscribers report")
    @RequestMapping(value = "/subscribers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MemberSubscriber>> getMemberSubscribersReport(
            @ApiParam(value = "Member Subscriber Request", required = true) @RequestBody MemberSubscriberRequest memberSubscriberRequest) {
        try {
            log.info("Member subscriber request: {}", JsonUtils.serializeJson(memberSubscriberRequest));

            List<MemberSubscriber> memberSubscribersData = memberReportService.memberSubscribers(memberSubscriberRequest);
            return new ResponseEntity<>(memberSubscribersData, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to getMemberSubscribersReport with exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get member details report data.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get the member subscribers report")
    @RequestMapping(value = "/subscribers/details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDetailTracker> getMemberSubscribersDetailsReport(
            @ApiParam(value = "Member Details Request", required = true) @RequestBody MemberDetailsRequest memberDetailsRequest) {
        try {
            log.info("Member details request: {}", JsonUtils.serializeJson(memberDetailsRequest));

            MemberDetailTracker memberSDetailsData = memberReportDetailsService.memberSubscriberDetails(memberDetailsRequest);
            return new ResponseEntity<>(memberSDetailsData, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to getMemberSubscribersDetailsReport with exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
