package com.payconiq.vo;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Prashant
 */
public class Stock {
    private Integer id;
    private String name;
    private double currentPrice;
    private Timestamp lastUpdate;

    public Integer getId() {
        return id;
    }

    public Stock(Integer id, String name, double currentPrice, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

    public Stock(Integer id, String name, double currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = Timestamp.from(Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;

        Stock stock = (Stock) o;

        if (Double.compare(stock.currentPrice, currentPrice) != 0) return false;
        if (!id.equals(stock.id)) return false;
        if (!name.equals(stock.name)) return false;
        return lastUpdate != null ? lastUpdate.equals(stock.lastUpdate) : stock.lastUpdate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(currentPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    public Stock() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
