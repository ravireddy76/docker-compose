package com.poc.kafka.repository;

import com.poc.kafka.model.ClaimsBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClaimBillingRepository class used to query claims billing data by query criteria.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Repository
@Transactional
public interface ClaimBillingRepository extends JpaRepository<ClaimsBilling, Integer> {

    /**
     * Method to find all claims for given member id.
     *
     * @param memberId
     * @return List
     */
    public List<ClaimsBilling> findByMemberId(String memberId);

    /**
     * Method to find all claims for billing.
     *
     * @return List
     */
    public List<ClaimsBilling> findAll();
}
