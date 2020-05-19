package com.poc.es.repository;

import com.poc.es.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> findByUserId(String userId);

    List<User> findByZipCode(String zipCode);
}
