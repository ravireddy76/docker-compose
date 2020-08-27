package com.poc.solr.repository;

import com.poc.solr.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends SolrCrudRepository<Order, Long> {

    Order findByOrderId(Long orderId);

    @Query("desc:*?0*")
    Page<Order> findByDescription(String searchTerm, Pageable pageable);

    @Query("desc:*?0* OR name:*?0* OR pname:*?0*")
    Page<Order> findByCustomQuery(String searchTerm, Pageable pageable);

    @Query(name = "Order.findByNamedQuery")
    Page<Order> findByNamedQuery(String searchTerm, Pageable pageable);

    @Query(value = "*:*")
    @Facet(fields = { "pname" })
    FacetPage<Order> findAllFacetsByProductName(Pageable pageable);
}
