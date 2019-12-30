package com.example.NetRDStockExchange;

import javax.persistence.*;


@Entity
@Table(name = "owned_stocks")
public class OwnedStocksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    private int userId;
//    private int stockId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockEntity stockEntity;
    @Column(name = "stock_amount")
    private int stockAmount;

    public int getId() {
        return id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public StockEntity getStockEntity() {
        return stockEntity;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setStockEntity(StockEntity stockEntity) {
        this.stockEntity = stockEntity;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    @Override
    public String toString() {
        return "OwnedStocksEntity{" +
                "id=" + id +
                "-stockName=" + stockEntity.getStockName() +
                "-stockCode=" + stockEntity.getStockCode() +
                "-stockAmount=" + stockAmount +
                "}_";
    }
}
