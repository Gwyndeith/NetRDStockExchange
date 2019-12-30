package com.example.NetRDStockExchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnedStocksRepository extends JpaRepository<OwnedStocksEntity, Integer> {

    OwnedStocksEntity findOwnedStocksEntitiesById(int stockId);

    void deleteOwnedStocksEntityById(int stockId);
}
