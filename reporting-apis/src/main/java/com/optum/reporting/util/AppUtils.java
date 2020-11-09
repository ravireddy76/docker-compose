package com.optum.reporting.util;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.optum.reporting.request.MemberSubscriberRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Basic logging practices for all controllers to print out required monitoring logs
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to E&A team. It's Illegal to reproduce this code.
 */
@Component
public class AppUtils {
    private static final String MEM_GROUP_ID_KEY = "memGroupIdKey";
    private static final String GOV_BENEFIT_TYPE_KEY = "govBenefitTypeKey";

    /**
     * Method to read file content for given file name.
     *
     * @param fileName
     * @return String
     * @throws Exception
     */
    public String readFileContent(String fileName) throws Exception {
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());

        Stream<String> lines = Files.lines(path);
        String fileContent = lines.collect(Collectors.joining("\n"));
        return fileContent;
    }

    /**
     * Method to read file data in the list style.
     *
     * @param fileName
     * @return List
     * @throws Exception
     */
    public List<String> readFileData(String fileName) throws Exception {
        List<String> records = new LinkedList();
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());

        Stream<String> lines = Files.lines(path);
        records = lines.collect(Collectors.toList());
        return records;
    }


    /**
     * Method to get member benefit coverage level code.
     *
     * @param reportData
     * @return LinkedHashMap<String, Object>
     */
    @SuppressWarnings("unchecked")
    public List<LinkedHashMap<String, Object>> getMemberBenefitCovLevelCode(LinkedHashMap<String, Object> reportData) {
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
     * Method to compute and build member report query for mongodb to execute.
     *
     * @param mGroupId
     * @param govtBenefitType
     * @param queryFilePath
     * @return String
     * @throws Exception
     */
    public String buildMemberReportQuery(String mGroupId, String govtBenefitType, String queryFilePath) throws Exception {
        String computedQuery;
        String memberGroupIdQuery = "{ $match: {\"subsaffiliation.memGroupID\": \"" + mGroupId + "\"} }";
        String govBenefitTypeQuery = "{ $match: {\"membercob.govtBenefitType\": \"" + govtBenefitType + "\"} }";

        /** Get the mongodb shell query and pass the input parameters to query. */
        String reportQuery = readFileContent(queryFilePath);

        /** Build the query based on user requested values. */
        if (StringUtils.isNotBlank(mGroupId) && StringUtils.isBlank(govtBenefitType)) {
            computedQuery = StringUtils.replace(reportQuery, MEM_GROUP_ID_KEY, memberGroupIdQuery);
            computedQuery = StringUtils.replace(computedQuery, GOV_BENEFIT_TYPE_KEY.concat(","), "");
        } else if (StringUtils.isNotBlank(govtBenefitType) && StringUtils.isBlank(mGroupId)) {
            computedQuery = StringUtils.replace(reportQuery, GOV_BENEFIT_TYPE_KEY, govBenefitTypeQuery);
            computedQuery = StringUtils.replace(computedQuery, MEM_GROUP_ID_KEY.concat(","), "");
        } else if (StringUtils.isNotBlank(mGroupId) && StringUtils.isNotBlank(govtBenefitType)) {
            computedQuery = StringUtils.replace(reportQuery, MEM_GROUP_ID_KEY, memberGroupIdQuery);
            computedQuery = StringUtils.replace(computedQuery, GOV_BENEFIT_TYPE_KEY, govBenefitTypeQuery);
        } else {
            throw new Exception("Both values can't be null or empty");
        }

        return computedQuery;
    }

}
