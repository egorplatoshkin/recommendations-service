package com.xm.models;

import com.opencsv.bean.CsvBindByName;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Price {
    @CsvBindByName(column = "timestamp")
    long timestamp;
    @CsvBindByName(column = "symbol")
    String symbol;
    @CsvBindByName(column = "price")
    double price;

    public long getTimestamp() {
        return timestamp;
    }

    public Instant getInstantTime() {
        return Instant.ofEpochMilli(timestamp);
    }

    public LocalDateTime getDate() {
        return LocalDateTime.ofInstant(this.getInstantTime(), ZoneId.systemDefault());
    }

    public Price setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Price setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Price setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Price price1 = (Price) o;

        if (getTimestamp() != price1.getTimestamp()) {
            return false;
        }
        if (Double.compare(price1.getPrice(), getPrice()) != 0) {
            return false;
        }
        return getSymbol() != null ? getSymbol().equals(price1.getSymbol()) : price1.getSymbol() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getTimestamp() ^ (getTimestamp() >>> 32));
        result = 31 * result + (getSymbol() != null ? getSymbol().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Price{" + "timestamp=" + getDate() + ", symbol='" + symbol + '\'' + ", price=" + price + "}\n";
    }
}
