package com.optum.hub.repository;

import com.optum.hub.model.MemberClaims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MemberClaimRepo class used to query member claims data by query criteria.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Repository
@Transactional
public interface MemberClaimRepo extends JpaRepository<MemberClaims, Integer> {

    /**
     * Method to find member claims by claim instruction code.
     *
     * @param claimInstCode
     * @return List
     */
    public List<MemberClaims> findByClaimInstCode(String claimInstCode);

}
