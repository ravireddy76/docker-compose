package com.optum.reporting.service;

import com.ee.dynamicmongoquery.MongoQuery;
import com.ee.dynamicmongoquery.MongoQueryParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.optum.reporting.model.MemberSubscriber;
import com.optum.reporting.model.SubscriberCategoryTracker;
import com.optum.reporting.request.MemberSubscriberRequest;
import com.optum.reporting.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * MemberReportService class used to query member collection in mondoDB and format the database result set.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to E&A team. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class MemberReportService {

    private static final String MEM_GROUP_ID_KEY = "memGroupIdKey";
    private static final String GOV_BENEFIT_TYPE_KEY = "govBenefitTypeKey";

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${queryfile.membersubscribers}")
    private String memberSubscribersQueryPath;


    /**
     * Method to query and get member subscribers data.
     *
     * @param memberSubscriberRequest
     * @return List
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MemberSubscriber> memberSubscribers(MemberSubscriberRequest memberSubscriberRequest) throws Exception {
        /** Get the db object from template. */
        DB mongoDB = mongoTemplate.getMongoDbFactory().getLegacyDb();

        /** Get the mongodb shell query and pass the input parameters to query. */
        String reportQuery = computedQuery(memberSubscriberRequest);
        HashMap<String, String> queryParams = new HashMap<>();

        /** Parse and execute the mongodb query. */
        MongoQueryParser parser = new MongoQueryParser();
        MongoQuery mongoQuery = parser.parse(reportQuery, queryParams);
        BasicDBList results = mongoQuery.execute(mongoDB);

        /** Compute and build the member subscribers report data.*/
        List<MemberSubscriber> memberSubscriberData = buildMemberSubscriberReportData(results, memberSubscriberRequest);
        return memberSubscriberData;
    }


    /**
     * Method to compute monthly subscribers and build the member subscriber report data.
     *
     * @param results
     * @param memberSubscriberRequest
     * @return List
     */
    @SuppressWarnings("unchecked")
    private List<MemberSubscriber> buildMemberSubscriberReportData(BasicDBList results, MemberSubscriberRequest memberSubscriberRequest) {
        Map<String, Double> monthTracking = new HashMap<>();
        List<LinkedHashMap<String, Object>> mBenefitCovLevelCodes = new ArrayList<>();

        /** Compute and Build the mongo query results. */
        for (Object basicDBObj : results) {
            BasicDBObject bdbObj = (BasicDBObject) basicDBObj;
            LinkedHashMap<String, Object> reportData = (LinkedHashMap<String, Object>) bdbObj.toMap();

            /* Get the member benefit coverage level report data. */
            List<LinkedHashMap<String, Object>> mBenefitCovLevelReportData = getMemberBenefitCovLevelCode(reportData);
            mBenefitCovLevelCodes.addAll(mBenefitCovLevelReportData);
        }

        /* Compute and build the coverage level code for months. */
        Map<String, SubscriberCategoryTracker> coverageLevelCodeByMonths = buildCoverageLevelCodeByMonthly(mBenefitCovLevelCodes);

        /* Build the member subscriber report data. */
        List<MemberSubscriber> memberSubscriberReportData = buildMemberSubscribers(coverageLevelCodeByMonths, memberSubscriberRequest);
        return memberSubscriberReportData;
    }


    /**
     * Method to build member subscribers by monthly.
     *
     * @param coverageLevelCodeByMonthly
     * @param memberSubscriberRequest
     * @return List<MemberSubscriber>
     */
    private static List<MemberSubscriber> buildMemberSubscribers(Map<String, SubscriberCategoryTracker> coverageLevelCodeByMonthly,
                                                                 MemberSubscriberRequest memberSubscriberRequest) {
        List<MemberSubscriber> monthlyMemberSubscribers = new ArrayList<>();

        for (Map.Entry<String, SubscriberCategoryTracker> entry : coverageLevelCodeByMonthly.entrySet()) {
            SubscriberCategoryTracker subCategoryTracker = entry.getValue();
            int totalMonthlySubscribers = subCategoryTracker.getEmpCounter() + subCategoryTracker.getEspCounter() +
                    subCategoryTracker.getEchCounter() + subCategoryTracker.getFamCounter();

            /* Build the member subscriber information. */
            MemberSubscriber memberSubscriber = new MemberSubscriber();
            memberSubscriber.setMemberGroup(memberSubscriberRequest.getMemberGroupId());
            memberSubscriber.setBenefitType(memberSubscriberRequest.getGovtBenefitType());
            memberSubscriber.setMembershipDate(entry.getKey());
            memberSubscriber.setSingleSubscribers(subCategoryTracker.getEmpCounter());
            memberSubscriber.setSubscribersSpouse(subCategoryTracker.getEspCounter());
            memberSubscriber.setSubscribersChild(subCategoryTracker.getEchCounter());
            memberSubscriber.setSubscribersFamily(subCategoryTracker.getFamCounter());
            memberSubscriber.setTotalSubscribers(totalMonthlySubscribers);
            monthlyMemberSubscribers.add(memberSubscriber);
        }
        return monthlyMemberSubscribers;
    }


    /**
     * Method to build the coverage level code by monthly.
     *
     * @param mBenefitCovLevelCodes
     * @return Map<String, SubscriberCategoryTracker>
     */
    private Map<String, SubscriberCategoryTracker> buildCoverageLevelCodeByMonthly(List<LinkedHashMap<String, Object>> mBenefitCovLevelCodes) {
        Map<String, SubscriberCategoryTracker> monthTrackingData = new HashMap<>();

        /* Collect the coverage level code for each month.  */
        for (LinkedHashMap<String, Object> mBenefitCovLevelCode : mBenefitCovLevelCodes) {
            /* Get the effective data and coverage level code. */
            String effectiveDate = (String) mBenefitCovLevelCode.get("effectiveDate");
            String coverageLevelCode = (String) mBenefitCovLevelCode.get("covLevelCode");
            String effectiveDateMonth = effectiveDate.substring(0, effectiveDate.length() - 3);

            /* Keep track month for each coverage level code occurred. */
            if (monthTrackingData.containsKey(effectiveDateMonth)) {
                SubscriberCategoryTracker existCategoryTracker = monthTrackingData.get(effectiveDateMonth);
                if (coverageLevelCode.equalsIgnoreCase("EMP")) {
                    existCategoryTracker.setEmpCounter(existCategoryTracker.getEmpCounter() + 1);
                } else if (coverageLevelCode.equalsIgnoreCase("ESP")) {
                    existCategoryTracker.setEspCounter(existCategoryTracker.getEspCounter() + 1);
                } else if (coverageLevelCode.equalsIgnoreCase("ECH")) {
                    existCategoryTracker.setEchCounter(existCategoryTracker.getEchCounter() + 1);
                } else if (coverageLevelCode.equalsIgnoreCase("FAM")) {
                    existCategoryTracker.setFamCounter(existCategoryTracker.getFamCounter() + 1);
                }

                monthTrackingData.put(effectiveDateMonth, existCategoryTracker);
            } else {
                SubscriberCategoryTracker subCategoryTracker = new SubscriberCategoryTracker();
                AtomicInteger atomicInteger = new AtomicInteger(0);

                if (coverageLevelCode.equalsIgnoreCase("EMP")) {
                    subCategoryTracker.setEmpCounter(atomicInteger.addAndGet(1));
                } else if (coverageLevelCode.equalsIgnoreCase("ESP")) {
                    subCategoryTracker.setEspCounter(atomicInteger.addAndGet(1));
                } else if (coverageLevelCode.equalsIgnoreCase("ECH")) {
                    subCategoryTracker.setEchCounter(atomicInteger.addAndGet(1));
                } else if (coverageLevelCode.equalsIgnoreCase("FAM")) {
                    subCategoryTracker.setFamCounter(atomicInteger.addAndGet(1));
                }

                monthTrackingData.put(effectiveDateMonth, subCategoryTracker);
            }
        }

        return monthTrackingData;
    }


    /**
     * Method to get member benefit coverage level code.
     *
     * @param reportData
     * @return LinkedHashMap<String, Object>
     */
    @SuppressWarnings("unchecked")
    private List<LinkedHashMap<String, Object>> getMemberBenefitCovLevelCode(LinkedHashMap<String, Object> reportData) {
        List<LinkedHashMap<String, Object>> memberBenefitCovLevelCodes = new ArrayList<>();

        BasicDBList memberBenefits = (BasicDBList) reportData.get("memberbenefit");
        for (Object mBenefit : memberBenefits) {
            BasicDBObject memberBenefit = (BasicDBObject) mBenefit;
            LinkedHashMap<String, Object> memBenefitReportData = (LinkedHashMap<String, Object>) memberBenefit.toMap();
            BasicDBList mBenefitCovLevelCodes = (BasicDBList) memBenefitReportData.get("memberbenefitcovlevelcode");

            /* Collect all the member benefit coverage level code information. */
            if (Objects.nonNull(mBenefitCovLevelCodes) && mBenefitCovLevelCodes.size() > 0) {
                for (Object mBenCovLevelCode : mBenefitCovLevelCodes) {
                    BasicDBObject mBenefitCovLevelCodeObj = (BasicDBObject) mBenCovLevelCode;
                    LinkedHashMap<String, Object> mBenefitCovLevelCode = (LinkedHashMap<String, Object>) mBenefitCovLevelCodeObj.toMap();
                    memberBenefitCovLevelCodes.add(mBenefitCovLevelCode);
                }
            }
        }
        return memberBenefitCovLevelCodes;
    }


    /**
     * Method to compute query for mongodb to execute.
     *
     * @param memberSubscriberReq
     * @return String
     * @throws Exception
     */
    private String computedQuery(MemberSubscriberRequest memberSubscriberReq) throws Exception {
        String computedQuery;
        String memberGroupIdQuery = "{ $match: {\"subsaffiliation.memGroupID\": \"" + memberSubscriberReq.getMemberGroupId() + "\"} }";
        String govBenefitTypeQuery = "{ $match: {\"membercob.govtBenefitType\": \"" + memberSubscriberReq.getGovtBenefitType() + "\"} }";

        /** Get the mongodb shell query and pass the input parameters to query. */
        String reportQuery = appUtils.readFileContent(memberSubscribersQueryPath);

        /** Build the query based on user requested values. */
        if (StringUtils.isNotBlank(memberSubscriberReq.getMemberGroupId()) && StringUtils.isBlank(memberSubscriberReq.getGovtBenefitType())) {
            computedQuery = StringUtils.replace(reportQuery, MEM_GROUP_ID_KEY, memberGroupIdQuery);
            computedQuery = StringUtils.replace(computedQuery, GOV_BENEFIT_TYPE_KEY.concat(","), "");
        } else if (StringUtils.isNotBlank(memberSubscriberReq.getGovtBenefitType()) && StringUtils.isBlank(memberSubscriberReq.getMemberGroupId())) {
            computedQuery = StringUtils.replace(reportQuery, GOV_BENEFIT_TYPE_KEY, govBenefitTypeQuery);
            computedQuery = StringUtils.replace(computedQuery, MEM_GROUP_ID_KEY.concat(","), "");
        } else if (StringUtils.isNotBlank(memberSubscriberReq.getMemberGroupId()) && StringUtils.isNotBlank(memberSubscriberReq.getGovtBenefitType())) {
            computedQuery = StringUtils.replace(reportQuery, MEM_GROUP_ID_KEY, memberGroupIdQuery);
            computedQuery = StringUtils.replace(computedQuery, GOV_BENEFIT_TYPE_KEY, govBenefitTypeQuery);
        } else {
            throw new Exception("Both values can't be null or empty");
        }

        return computedQuery;
    }

}
