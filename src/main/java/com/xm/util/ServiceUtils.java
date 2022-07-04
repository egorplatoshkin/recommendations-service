package com.xm.util;

import com.xm.models.Price;
import java.util.Comparator;
import java.util.List;

public class ServiceUtils {
    public static final Comparator<Price> PRICE_COMPARATOR = Comparator.comparingDouble(Price::getPrice);
    public static final Comparator<Price> TIMESTAMP_COMPARATOR = Comparator.comparingLong(Price::getTimestamp);

    /**
     * Count normalized range value by formula (max-min)/min
     * @param prices - list of filtered by crypto prices
     * @return normalized range
     */
    public static Double countNormalizedRange(List<Price> prices) {
        double minPrice = prices.stream().min(ServiceUtils.PRICE_COMPARATOR).map(Price::getPrice).orElse(0D);
        double maxPrice = prices.stream().max(ServiceUtils.PRICE_COMPARATOR).map(Price::getPrice).orElse(0D);
        return minPrice == 0D ? Double.NaN : (maxPrice - minPrice) / minPrice;
    }
}
