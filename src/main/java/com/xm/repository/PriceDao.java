package com.xm.repository;

import com.xm.models.Price;
import java.time.LocalDate;
import java.util.List;

public interface PriceDao {
    /**
     * Retrieve all available crypto short names
     * @return List of cryptos names (e.g. BTC, LTC)
     */
    List<String> getAllCryptoNames();

    /**
     * Retrieve all available prices by criteria
     * @param crypto - crypto short name (e.g. BTC, LTC)
     * @return List of prices
     */
    List<Price> findByCrypto(String crypto);
    /**
     * Retrieve all available prices by criteria
     * @param date - price created on date (e.g. BTC, LTC)
     * @return List of prices
     */
    List<Price> findByDate(LocalDate date);
}
