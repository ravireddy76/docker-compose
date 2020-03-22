package com.optum.hub.service;

import com.optum.hub.model.DataResult;
import com.optum.hub.model.MemberClaims;
import com.optum.hub.repository.DataResultRepo;
import com.optum.hub.repository.MemberClaimRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClaimIdentifyService class used to search and compute claims and ingest data results into mongodb.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Service
@Slf4j
public class ClaimIdentifyService {

    @Autowired
    MemberClaimRepo memberClaimRepo;

    @Autowired
    DataResultRepo dataResultRepo;


    /**
     * Method to search and compute claims and ingest data results into mongoDB.
     *
     * @param claimInstructionCode
     */
    public void searchAndComputeClaims(String claimInstructionCode) {

        /* Get the searched claims by claims instruction code. */
        List<MemberClaims> searchedClaims = memberClaimRepo.findByClaimInstCode(claimInstructionCode);
        String collectionName = configuredCollections().get(claimInstructionCode);
        log.info("Number of searched claims: {}, collectionName: {}", searchedClaims.size(), collectionName);

        /* Ingest the processed data results data into mongoDB for given collection. */
        for (MemberClaims memberClaims : searchedClaims) {
            /* Ingest data results into mongodb. */
            DataResult dataResult = buildDataResult(memberClaims);
            dataResultRepo.saveDataResults(collectionName, dataResult);
        }
    }


    /**
     * Method to build data result.
     *
     * @param memberClaims
     * @return DataResult
     */
    private DataResult buildDataResult(MemberClaims memberClaims) {
        DataResult dataResult = new DataResult();
        dataResult.setId(memberClaims.getClaimInstCode().concat(String.valueOf(RandomUtils.nextInt())));
        dataResult.setMemberId(memberClaims.getMemberId());
        dataResult.setMemberName(memberClaims.getMemberName());
        dataResult.setClaimId(memberClaims.getClaimId());
        dataResult.setClaimInstCode(memberClaims.getClaimInstCode());
        dataResult.setCreatedDate(memberClaims.getCreatedDate().toString());
        return dataResult;
    }

    /**
     * Method to build claims instruction code and collection name mapping configuration.
     *
     * @return Map
     */
    private Map<String, String> configuredCollections() {
        Map<String, String> configuredCollection = new HashMap<>();
        configuredCollection.put("P", "provider_claims");
        configuredCollection.put("I", "inpatient_claims");
        configuredCollection.put("O", "outpatient_claims");
        return configuredCollection;
    }

}
