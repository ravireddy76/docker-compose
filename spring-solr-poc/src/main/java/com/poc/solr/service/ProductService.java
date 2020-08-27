package com.poc.solr.service;

import com.poc.solr.model.Product;
import com.poc.solr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductService class acts like delegate between controller and repository layer to handle business cases.
 *
 * @author Ravi
 * @CopyRight (C) All rights reserved to Ravi POC Inc. It's Illegal to reproduce this code.
 */
@Service
public class ProductService {
    @Value("${page.size}")
    private int pageSize;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Method to create product and ingest data into solr collection.
     *
     * @param product
     * @return Product
     */
    public Product create(Product product) {
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    /**
     * Method to update product and ingest data into solr collection.
     *
     * @param product
     * @return
     */
    public Product update(Product product) {
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    /**
     * Method to delete product information from solr collection for given product name.
     *
     * @param productName
     * @return
     */
    public String delete(String productName) {
        List<Product> searchedProducts = productRepository.findByName(productName);
        productRepository.deleteAll(searchedProducts);

        String deleteMsg = "Products got deleted for product name: ".concat(productName);
        return deleteMsg;
    }

    /**
     * Method to search product by name.
     *
     * @param productName
     * @return List
     */
    public List<Product> getProductsByName(String productName) {
        List<Product> searchedProducts = productRepository.findByName(productName);
        return searchedProducts;
    }

    /**
     * Method to search products by custom query with pagination.
     *
     * @param searchTerm
     * @param pageNumber
     * @return List
     */
    public List<Product> getProductsByCustomQuery(String searchTerm, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Product> searchedProducts = productRepository.findByCustomQuery(searchTerm, pageRequest).getContent();
        return searchedProducts;
    }

    /**
     * Method to search products by named query with pagination.
     *
     * @param searchTerm
     * @param pageNumber
     * @return List
     */
    public List<Product> getProductsByNamedQuery(String searchTerm, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Product> searchedProducts = productRepository.findByNamedQuery(searchTerm, pageRequest).getContent();
        return searchedProducts;
    }

}
