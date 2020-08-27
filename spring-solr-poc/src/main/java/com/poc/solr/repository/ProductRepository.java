package com.poc.solr.repository;

import com.poc.solr.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends SolrCrudRepository<Product, String> {

    List<Product> findByName(String name);

    @Query("id:*?0* OR name:*?0*")
    Page<Product> findByCustomQuery(String searchTerm, Pageable pageable);

    @Query(name = "Product.findByNamedQuery")
    Page<Product> findByNamedQuery(String searchTerm, Pageable pageable);
}
