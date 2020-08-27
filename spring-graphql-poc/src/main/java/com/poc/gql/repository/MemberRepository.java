package com.poc.gql.repository;

import com.poc.gql.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {

}
