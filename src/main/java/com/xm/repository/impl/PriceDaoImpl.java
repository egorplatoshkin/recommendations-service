package com.xm.repository.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.xm.models.Price;
import com.xm.repository.PriceDao;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import static java.time.temporal.ChronoUnit.DAYS;

@Repository
public class PriceDaoImpl implements PriceDao {

    private List<Price> prices;

    /**
     * Method for initializing prices data from csv files.
     * Be executed after dependency injection.
     */
    @PostConstruct
    private void init() {
        prices = Stream.of(Objects.requireNonNull(new File(
                Objects.requireNonNull(getClass().getResource("/static/prices/"), "Can't load resources")
                        .getPath()).listFiles(), "Can't list files")).map(file -> {
            try (var reader = new FileReader(file)) {
                return new CsvToBeanBuilder<Price>(reader).withType(Price.class).build().parse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).toList();
    }

    @Override
    public List<String> getAllCryptoNames() {
        return prices.stream().map(Price::getSymbol).distinct().toList();
    }

    @Override
    public List<Price> findByCrypto(String crypto) {
        return prices.stream().filter(price -> crypto.equalsIgnoreCase(price.getSymbol())).toList();
    }

    @Override
    public List<Price> findByDate(LocalDate date) {
        return prices.stream()
                .filter(price -> LocalDate.ofInstant(price.getInstantTime().truncatedTo(DAYS), ZoneId.systemDefault())
                        .equals(date)).toList();
    }
}
