package com.xm.service;

import com.xm.models.Price;
import java.util.Optional;

public interface PriceService {
    /**
     * Retrieve max available price by criteria
     * @param crypto - crypto short name (e.g. BTC, LTC)
     * @return max available price
     */
    Optional<Price> findMaxForCrypto(String crypto);
    /**
     * Retrieve min available price by criteria
     * @param crypto - crypto short name (e.g. BTC, LTC)
     * @return min available price
     */
    Optional<Price> findMinForCrypto(String crypto);
    /**
     * Retrieve the newest available price by criteria
     * @param crypto - crypto short name (e.g. BTC, LTC)
     * @return newest available price
     */
    Optional<Price> findNewestForCrypto(String crypto);
    /**
     * Retrieve the oldest available price by criteria
     * @param crypto - crypto short name (e.g. BTC, LTC)
     * @return oldest available price
     */
    Optional<Price> findOldestForCrypto(String crypto);
}
