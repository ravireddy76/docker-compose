package com.poc.mssql.service;

import com.poc.mssql.domain.Stock;
import com.poc.mssql.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepo stockRepo;

    public Stock saveStock(Stock stock) {
        Stock savedStock = stockRepo.saveAndFlush(stock);
        return savedStock;
    }

    public void deleteStock(Integer stockId) {
        Optional<Stock> searchedStock = stockRepo.findById(stockId);
        stockRepo.delete(searchedStock.get());
        System.out.println("Stock is deleted for stock id:" + stockId);
    }

    public Stock searchStockById(Integer stockId) {
        Optional<Stock> searchedStock = stockRepo.findById(stockId);
        return searchedStock.get();
    }
}
