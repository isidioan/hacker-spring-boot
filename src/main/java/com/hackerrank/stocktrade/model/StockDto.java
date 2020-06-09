package com.hackerrank.stocktrade.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

/**
 * @author ioannou
 */
@JsonPropertyOrder({ "symbol", "lowest", "highest" })
public class StockDto implements Serializable {

    private String symbol;
    private Float highest;
    private Float lowest;

    public StockDto(String symbol, Float highest, Float lowest) {
        this.symbol = symbol;
        this.highest = highest;
        this.lowest = lowest;
    }

    public StockDto() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getHighest() {
        return highest;
    }

    public void setHighest(Float highest) {
        this.highest = highest;
    }

    public Float getLowest() {
        return lowest;
    }

    public void setLowest(Float lowest) {
        this.lowest = lowest;
    }
}
