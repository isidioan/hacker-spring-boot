package com.hackerrank.stocktrade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@JsonPropertyOrder({ "symbol", "timestamp", "shares", "id", "user", "type" })
public class Trade implements Serializable {
    @Id
    private Long id;

    private String type;

    @ManyToOne
    private User user;

    @JsonProperty("symbol")
    private String stockSymbol;

    @JsonProperty("shares")
    private Integer stockQuantity;

    @JsonProperty("price")
    private Float stockPrice;

    @JsonProperty("timestamp")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp tradeTimestamp;

    public Trade() {
    }

    public Trade(Long id, String type, User user, String stockSymbol, Integer stockQuantity, Float stockPrice, Timestamp tradeTimestamp) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.stockQuantity = stockQuantity;
        this.stockPrice = stockPrice;
        this.tradeTimestamp = tradeTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Timestamp getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(Timestamp tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }
}
