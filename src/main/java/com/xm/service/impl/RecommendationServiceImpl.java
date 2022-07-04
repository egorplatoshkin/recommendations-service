package com.xm.service.impl;

import com.xm.models.Price;
import com.xm.models.Recommendation;
import com.xm.repository.PriceDao;
import com.xm.service.RecommendationService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.xm.util.ServiceUtils.countNormalizedRange;
import static java.util.Comparator.comparingDouble;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final PriceDao priceDao;

    @Autowired
    public RecommendationServiceImpl(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    @Override
    public List<Recommendation> getAllSortByRangeDesc() {
        return priceDao.getAllCryptoNames().stream().map(crypto -> new Recommendation().setCrypto(crypto)
                        .setNormalizedRange(countNormalizedRange(priceDao.findByCrypto(crypto))))
                .sorted(comparingDouble(Recommendation::getNormalizedRange).reversed()).toList();
    }

    @Override
    public Optional<Recommendation> getHighestRangeForDate(LocalDate date) {
        List<Price> pricesByDate = priceDao.findByDate(date);
        List<String> cryptosAvailableByDate =
                priceDao.findByDate(date).stream().map(Price::getSymbol).distinct().toList();
        return cryptosAvailableByDate.stream().map(crypto -> new Recommendation().setCrypto(crypto).setNormalizedRange(
                        countNormalizedRange(
                                pricesByDate.stream().filter(price -> crypto.equalsIgnoreCase(price.getSymbol())).toList())))
                .max(comparingDouble(Recommendation::getNormalizedRange));
    }
}
