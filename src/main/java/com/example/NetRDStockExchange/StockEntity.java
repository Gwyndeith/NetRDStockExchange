package com.example.NetRDStockExchange;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String stockName;
    private String stockCode;
    private double price;
    private String lastModifiedBy;

    @OneToMany(mappedBy = "stockEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OwnedStocksEntity> ownedStocksEntityList;

    public StockEntity() {

    }

    public StockEntity(String stockName, String stockCode) {
        this.stockName = stockName;
        this.stockCode = stockCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockPrice(double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setOwnedStocksEntityList(List<OwnedStocksEntity> ownedStocksEntityList) {
        this.ownedStocksEntityList = ownedStocksEntityList;
    }

    public int getId() {
        return id;
    }

    public String getStockName() {
        return stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public double getPrice() {
        return price;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public List<OwnedStocksEntity> getOwnedStocksEntityList() {
        return ownedStocksEntityList;
    }


    @Override
    public String toString() {
        return "StockEntity{" +
                "id=" + id +
                "-stockName='" + stockName + '\'' +
                "-stockCode='" + stockCode + '\'' +
                "-price=" + price +
                "-lastModifiedBy='" + lastModifiedBy + '\'' +
                "}_";
    }
}
