package com.xm.service.impl;

import com.xm.models.Price;
import com.xm.repository.PriceDao;
import com.xm.service.PriceService;
import java.util.Optional;
import org.springframework.stereotype.Service;

import static com.xm.util.ServiceUtils.PRICE_COMPARATOR;
import static com.xm.util.ServiceUtils.TIMESTAMP_COMPARATOR;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceDao priceDao;

    public PriceServiceImpl(PriceDao priceDao) {
        this.priceDao = priceDao;
    }
    @Override
    public Optional<Price> findMaxForCrypto(String crypto) {
        return priceDao.findByCrypto(crypto).stream().max(PRICE_COMPARATOR);
    }

    @Override
    public Optional<Price> findMinForCrypto(String crypto) {
        return priceDao.findByCrypto(crypto).stream().min(PRICE_COMPARATOR);
    }

    @Override
    public Optional<Price> findNewestForCrypto(String crypto) {
        return priceDao.findByCrypto(crypto).stream().max(TIMESTAMP_COMPARATOR);
    }

    @Override
    public Optional<Price> findOldestForCrypto(String crypto) {
        return priceDao.findByCrypto(crypto).stream().min(TIMESTAMP_COMPARATOR);
    }

}
