package com.optum.reporting.service;

import com.ee.dynamicmongoquery.MongoQuery;
import com.ee.dynamicmongoquery.MongoQueryParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.optum.reporting.model.MemberDetailTracker;
import com.optum.reporting.model.MemberSubscriberDetails;
import com.optum.reporting.request.MemberDetailsRequest;
import com.optum.reporting.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * MemberReportService class used to query member collection in mondoDB and format the database result set.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to E&A team. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class MemberReportDetailsService {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${queryfile.membersubscriberdetails}")
    private String memberReportDetailsQueryPath;


    /**
     * Method to query and get member subscribers data.
     *
     * @param mDetailsRequest
     * @return List
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public MemberDetailTracker memberSubscriberDetails(MemberDetailsRequest mDetailsRequest) throws Exception {
        /** Get the db object from template. */
        DB mongoDB = mongoTemplate.getMongoDbFactory().getLegacyDb();

        /** Get the mongodb shell query and pass the input parameters to query. */
        String reportQuery = appUtils.buildMemberReportQuery(mDetailsRequest.getMemberGroupId(),
                mDetailsRequest.getGovtBenefitType(), memberReportDetailsQueryPath);

        HashMap<String, String> queryParams = new HashMap<>();

        /** Parse and execute the mongodb query. */
        MongoQueryParser parser = new MongoQueryParser();
        MongoQuery mongoQuery = parser.parse(reportQuery, queryParams);
        BasicDBList results = mongoQuery.execute(mongoDB);

        /** Compute and build the member subscribers report data.*/
        MemberDetailTracker memberSubscriberDetails = buildMemberReportDetailsData(results, mDetailsRequest);
        return memberSubscriberDetails;
    }


    /**
     * Method to compute monthly subscribers and build the member subscriber report data.
     *
     * @param results
     * @param mDetailsRequest
     * @return List
     */
    @SuppressWarnings("unchecked")
    private MemberDetailTracker buildMemberReportDetailsData(BasicDBList results, MemberDetailsRequest mDetailsRequest) {
        Map<String, Double> monthTracking = new HashMap<>();
        List<MemberSubscriberDetails> mSubscriberDetails = new ArrayList<>();

        /** Compute and Build the mongo query results. */
        for (Object basicDBObj : results) {
            BasicDBObject bdbObj = (BasicDBObject) basicDBObj;
            LinkedHashMap<String, Object> reportData = (LinkedHashMap<String, Object>) bdbObj.toMap();

            /* Get and build the member subscriber details. */
            MemberSubscriberDetails memberSubscriberDetails = buildMemberSubscriberDetails(reportData, mDetailsRequest);
            mSubscriberDetails.add(memberSubscriberDetails);
        }

        /* Compute and build member details information for each month. */
        Map<String, MemberDetailTracker> mSubscriberDetailsByMonthly = buildMemberSubscribersDetailsByMonthly(mSubscriberDetails);

        /* Get the member subscribers details information for given month. */
        MemberDetailTracker memberDetails = mSubscriberDetailsByMonthly.get(mDetailsRequest.getSubscriberMonthYear());
        return memberDetails;
    }

    /**
     * Method to build the member subscriber details.
     *
     * @param reportData
     * @param mDetailsRequest
     * @return MemberSubscriberDetails
     */
    private MemberSubscriberDetails buildMemberSubscriberDetails(LinkedHashMap<String, Object> reportData, MemberDetailsRequest mDetailsRequest) {
        MemberSubscriberDetails memberSubscriberDetails = new MemberSubscriberDetails();

        /* Get the member benefit coverage level report data. */
        List<LinkedHashMap<String, Object>> mBenefitCovLevelReportData = appUtils.getMemberBenefitCovLevelCode(reportData);
        String address2 = StringUtils.isNotBlank((String) reportData.get("address2")) ? (String) reportData.get("address2") : "";

        /* Populate member subscriber details.*/
        memberSubscriberDetails.setMBenefitCovLevelReportData(mBenefitCovLevelReportData);
        memberSubscriberDetails.setMemberGroup(mDetailsRequest.getMemberGroupId());
        memberSubscriberDetails.setBenefitType(mDetailsRequest.getGovtBenefitType());
        memberSubscriberDetails.setName((String) reportData.get("memberName"));
        memberSubscriberDetails.setDateOfBirth((String) reportData.get("dob"));
        memberSubscriberDetails.setGender((String) reportData.get("gender"));
        memberSubscriberDetails.setBranding((String) reportData.get("branding"));
        memberSubscriberDetails.setAddress1((String) reportData.get("address1"));
        memberSubscriberDetails.setAddress2(address2);
        memberSubscriberDetails.setCity((String) reportData.get("city"));
        memberSubscriberDetails.setState((String) reportData.get("state"));
        memberSubscriberDetails.setZipCode((String) reportData.get("zipCode"));

        /* Build the member address. */
        String completeAddress = memberSubscriberDetails.getAddress1().concat(", ").concat(memberSubscriberDetails.getAddress2()).concat(", ")
                .concat(memberSubscriberDetails.getCity()).concat(" ").concat(memberSubscriberDetails.getState()).concat("-")
                .concat(memberSubscriberDetails.getZipCode());

        memberSubscriberDetails.setCompleteAddress(completeAddress);
        return memberSubscriberDetails;
    }


    /**
     * Method to build the coverage level code by monthly.
     *
     * @param mSubscriberDetails
     * @return Map
     */
    private Map<String, MemberDetailTracker> buildMemberSubscribersDetailsByMonthly(List<MemberSubscriberDetails> mSubscriberDetails) {
        Map<String, MemberDetailTracker> monthTrackingData = new HashMap<>();

        /* Collect the coverage level code for each month.  */
        for (MemberSubscriberDetails memberSubscriberDetails : mSubscriberDetails) {
            for (LinkedHashMap<String, Object> mBenefitCovLevelCode : memberSubscriberDetails.getMBenefitCovLevelReportData()) {
                /* Get the effective data and coverage level code. */
                String effectiveDate = (String) mBenefitCovLevelCode.get("effectiveDate");
                String coverageLevelCode = (String) mBenefitCovLevelCode.get("covLevelCode");
                String effectiveDateMonth = effectiveDate.substring(0, effectiveDate.length() - 3);

                /* Keep track month for each coverage level code occurred. */
                if (monthTrackingData.containsKey(effectiveDateMonth)) {
                    MemberDetailTracker existMemberTracker = monthTrackingData.get(effectiveDateMonth);
                    if (coverageLevelCode.equalsIgnoreCase("EMP")) {
                        existMemberTracker.getEmpMembers().add(memberSubscriberDetails);
                    } else if (coverageLevelCode.equalsIgnoreCase("ESP")) {
                        existMemberTracker.getEspMembers().add(memberSubscriberDetails);
                    } else if (coverageLevelCode.equalsIgnoreCase("ECH")) {
                        existMemberTracker.getEchMembers().add(memberSubscriberDetails);
                    } else if (coverageLevelCode.equalsIgnoreCase("FAM")) {
                        existMemberTracker.getFamMembers().add(memberSubscriberDetails);
                    }

                    monthTrackingData.put(effectiveDateMonth, existMemberTracker);
                } else {
                    MemberDetailTracker memberDetailTracker = new MemberDetailTracker();
                    if (coverageLevelCode.equalsIgnoreCase("EMP")) {
                        memberDetailTracker.getEmpMembers().add(memberSubscriberDetails);
                    } else if (coverageLevelCode.equalsIgnoreCase("ESP")) {
                        memberDetailTracker.getEspMembers().add(memberSubscriberDetails);
                    } else if (coverageLevelCode.equalsIgnoreCase("ECH")) {
                        memberDetailTracker.getEchMembers().add(memberSubscriberDetails);
                    } else if (coverageLevelCode.equalsIgnoreCase("FAM")) {
                        memberDetailTracker.getFamMembers().add(memberSubscriberDetails);
                    }

                    monthTrackingData.put(effectiveDateMonth, memberDetailTracker);
                }
            }
        }

        return monthTrackingData;
    }

}
