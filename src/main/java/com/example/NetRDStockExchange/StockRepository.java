package com.example.NetRDStockExchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer> {

    StockEntity findStockEntityByStockName(String stockName);

    void deleteStockById(int stockId);
}
