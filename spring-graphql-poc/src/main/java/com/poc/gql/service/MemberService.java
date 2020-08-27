package com.poc.gql.service;

import com.poc.gql.model.Member;
import com.poc.gql.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private static final String MEMBER_COLL = "member";

    @Autowired
    private DataRepository dataRepository;

    public void saveOrUpdate(Member member){
        dataRepository.saveOrUpdate(member, MEMBER_COLL);
    }

    public void delete(Member member){
        dataRepository.saveOrUpdate(member, MEMBER_COLL);
    }
    
}
