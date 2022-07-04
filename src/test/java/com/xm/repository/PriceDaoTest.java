package com.xm.repository;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceDaoTest {

    public static final List<String> CRYPTO_LIST = List.of("BTC", "DOGE", "ETH", "LTC", "XRP");
    @Autowired
    PriceDao priceDao;

    @Test
    void getAllCryptoNames() {
        //Expected: lists of crypto would be the same
        assertLinesMatch(CRYPTO_LIST, priceDao.getAllCryptoNames());
    }

    @Test
    void findByCryptoIgnoreCase() {
        //Expected: lists of crypto would search in any case
        assertEquals(100, priceDao.findByCrypto("BTC").size());
        assertEquals(90, priceDao.findByCrypto("DOGE").size());
        assertEquals(85, priceDao.findByCrypto("LTC").size());
        assertEquals(95, priceDao.findByCrypto("ETH").size());
        assertEquals(80, priceDao.findByCrypto("XRP").size());
        int totalSize = priceDao.findByCrypto("BTC").size() + priceDao.findByCrypto("DOGE").size() + priceDao.findByCrypto(
                "LTC").size() + priceDao.findByCrypto("ETH").size() + priceDao.findByCrypto("XRP").size();
        assertEquals(450, totalSize);
        assertEquals(100, priceDao.findByCrypto("btc").size());
        assertEquals(90, priceDao.findByCrypto("doge").size());
        assertEquals(85, priceDao.findByCrypto("ltc").size());
        assertEquals(95, priceDao.findByCrypto("eth").size());
        assertEquals(80, priceDao.findByCrypto("xrp").size());
        assertEquals(100, priceDao.findByCrypto("bTC").size());
        assertEquals(90, priceDao.findByCrypto("dOGE").size());
        assertEquals(85, priceDao.findByCrypto("lTC").size());
        assertEquals(95, priceDao.findByCrypto("eTH").size());
        assertEquals(80, priceDao.findByCrypto("xRP").size());

    }

    @Test
    void findByCryptoIgnoreUnexpectedInputs() {
        //Expected: lists of crypto would be empty if search by unexpected crypto symbols
        assertEquals(priceDao.findByCrypto("TBC").size(), 0);
        assertEquals(priceDao.findByCrypto("ODGE").size(), 0);
        assertEquals(priceDao.findByCrypto("TLC").size(), 0);
        assertEquals(priceDao.findByCrypto("TEH").size(), 0);
        assertEquals(priceDao.findByCrypto("RXP").size(), 0);
        assertEquals(priceDao.findByCrypto("tbc").size(), 0);
        assertEquals(priceDao.findByCrypto("odge").size(), 0);
        assertEquals(priceDao.findByCrypto("tlc").size(), 0);
        assertEquals(priceDao.findByCrypto("teh").size(), 0);
        assertEquals(priceDao.findByCrypto("rxp").size(), 0);
        assertEquals(priceDao.findByCrypto("TbC").size(), 0);
        assertEquals(priceDao.findByCrypto("OdGE").size(), 0);
        assertEquals(priceDao.findByCrypto("TlC").size(), 0);
        assertEquals(priceDao.findByCrypto("TeH").size(), 0);
        assertEquals(priceDao.findByCrypto("RxP").size(), 0);
    }

    @Test
    void findByDate() {
        //Expected: that all values created on January
        assertEquals(18, priceDao.findByDate(LocalDate.of(2022, 1, 1)).size());
        assertEquals(14, priceDao.findByDate(LocalDate.of(2022, 1, 2)).size());
        assertEquals(15, priceDao.findByDate(LocalDate.of(2022, 1, 3)).size());
        assertEquals(10, priceDao.findByDate(LocalDate.of(2022, 1, 4)).size());
        assertEquals(19, priceDao.findByDate(LocalDate.of(2022, 1, 5)).size());
        assertEquals(10, priceDao.findByDate(LocalDate.of(2022, 1, 6)).size());
        assertEquals(11, priceDao.findByDate(LocalDate.of(2022, 1, 7)).size());
        assertEquals(13, priceDao.findByDate(LocalDate.of(2022, 1, 8)).size());
        assertEquals(12, priceDao.findByDate(LocalDate.of(2022, 1, 9)).size());
        assertEquals(15, priceDao.findByDate(LocalDate.of(2022, 1, 10)).size());
        assertEquals(16, priceDao.findByDate(LocalDate.of(2022, 1, 11)).size());
        assertEquals(11, priceDao.findByDate(LocalDate.of(2022, 1, 12)).size());
        assertEquals(17, priceDao.findByDate(LocalDate.of(2022, 1, 13)).size());
        assertEquals(22, priceDao.findByDate(LocalDate.of(2022, 1, 14)).size());
        assertEquals(6, priceDao.findByDate(LocalDate.of(2022, 1, 15)).size());
        assertEquals(14, priceDao.findByDate(LocalDate.of(2022, 1, 16)).size());
        assertEquals(15, priceDao.findByDate(LocalDate.of(2022, 1, 17)).size());
        assertEquals(14, priceDao.findByDate(LocalDate.of(2022, 1, 18)).size());
        assertEquals(14, priceDao.findByDate(LocalDate.of(2022, 1, 19)).size());
        assertEquals(15, priceDao.findByDate(LocalDate.of(2022, 1, 20)).size());
        assertEquals(12, priceDao.findByDate(LocalDate.of(2022, 1, 21)).size());
        assertEquals(18, priceDao.findByDate(LocalDate.of(2022, 1, 22)).size());
        assertEquals(21, priceDao.findByDate(LocalDate.of(2022, 1, 23)).size());
        assertEquals(18, priceDao.findByDate(LocalDate.of(2022, 1, 24)).size());
        assertEquals(12, priceDao.findByDate(LocalDate.of(2022, 1, 25)).size());
        assertEquals(14, priceDao.findByDate(LocalDate.of(2022, 1, 26)).size());
        assertEquals(14, priceDao.findByDate(LocalDate.of(2022, 1, 27)).size());
        assertEquals(16, priceDao.findByDate(LocalDate.of(2022, 1, 28)).size());
        assertEquals(19, priceDao.findByDate(LocalDate.of(2022, 1, 29)).size());
        assertEquals(12, priceDao.findByDate(LocalDate.of(2022, 1, 30)).size());
        assertEquals(13, priceDao.findByDate(LocalDate.of(2022, 1, 31)).size());
        assertEquals(0, priceDao.findByDate(LocalDate.of(2022, 2, 1)).size());
        int total = 0;
        for (int i = 1; i <= 31; i++) {
            int size = priceDao.findByDate(LocalDate.of(2022, 1, i)).size();
            total += size;
        }
        assertEquals(450, total);
    }
}