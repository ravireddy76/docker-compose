package com.poc.gql.repository;

import com.poc.gql.model.Claim;
import com.poc.gql.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> void saveOrUpdate(T entity, String collectionName) {
        mongoTemplate.save(entity, collectionName);
    }

    public <T> void delete(T entity, String collectionName) {
        mongoTemplate.remove(entity, collectionName);
    }

    public <T> List<? extends Object> findAll(T entity, String collectionName) {
        return mongoTemplate.findAll(entity.getClass(), collectionName);
    }

    public Member findMemberById(Member member, String collectionName) {
        Query query = new Query(Criteria.where("_id").is(member.getId()));
        Member searchedMember = mongoTemplate.findOne(query, Member.class, collectionName);
        return searchedMember;
    }

    public Member findClaimById(Claim claim, String collectionName) {
        Query query = new Query(Criteria.where("_id").is(claim.getId()));
        Member searchedMember = mongoTemplate.findOne(query, Member.class, collectionName);
        return searchedMember;
    }
}
