package com.xm.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceServiceTest {

    @Autowired
    private PriceService priceService;

    @Test
    void findMaxForCryptoIgnoreCaseWorks() {
        assertEquals(47722.66, priceService.findMaxForCrypto("BTC").get().getPrice());
        assertEquals(0.1941, priceService.findMaxForCrypto("DOGE").get().getPrice());
        assertEquals(3828.11, priceService.findMaxForCrypto("ETH").get().getPrice());
        assertEquals(151.5, priceService.findMaxForCrypto("LTC").get().getPrice());
        assertEquals(0.8458, priceService.findMaxForCrypto("xrp").get().getPrice());
    }

    @Test
    void findMaxForUnexpectedCryptoReturnsEmptyOptional() {
        //Expected: findMaxForCrypto for unexpected input returns empty optional
        assertEquals(Optional.empty(), priceService.findMaxForCrypto("BTCq"));
    }

    @Test
    void findMinForCryptoWorks() {
        assertEquals(33276.59, priceService.findMinForCrypto("BTC").get().getPrice());
        assertEquals(0.129, priceService.findMinForCrypto("DOGE").get().getPrice());
        assertEquals(2336.52, priceService.findMinForCrypto("ETH").get().getPrice());
        assertEquals(103.4, priceService.findMinForCrypto("LTC").get().getPrice());
        assertEquals(0.5616, priceService.findMinForCrypto("XRP").get().getPrice());
    }

    @Test
    void findMinForUnexpectedCryptoReturnsEmptyOptional() {
        //Expected: findMaxForCrypto for unexpected input returns empty optional
        assertEquals(Optional.empty(), priceService.findMinForCrypto("BTCq"));
    }

    @Test
    void findNewestForCryptoWorks() {
        assertEquals("2022-01-31T22:00", priceService.findNewestForCrypto("BTC").get().getDate().toString());
        assertEquals("2022-01-31T21:00", priceService.findNewestForCrypto("DOGE").get().getDate().toString());
        assertEquals("2022-01-31T22:00", priceService.findNewestForCrypto("ETH").get().getDate().toString());
        assertEquals("2022-01-31T19:00", priceService.findNewestForCrypto("LTC").get().getDate().toString());
        assertEquals("2022-01-31T03:00", priceService.findNewestForCrypto("XRP").get().getDate().toString());
    }

    @Test
    void findNewestForUnexpectedCryptoReturnsEmptyOptional() {
        //Expected: findMaxForCrypto for unexpected input returns empty optional
        assertEquals(Optional.empty(), priceService.findMinForCrypto("BTCq"));
    }

    @Test
    void findOldestForCryptoWorks() {
        assertEquals("2022-01-01T06:00", priceService.findOldestForCrypto("BTC").get().getDate().toString());
        assertEquals("2022-01-01T07:00", priceService.findOldestForCrypto("DOGE").get().getDate().toString());
        assertEquals("2022-01-01T10:00", priceService.findOldestForCrypto("ETH").get().getDate().toString());
        assertEquals("2022-01-01T08:00", priceService.findOldestForCrypto("LTC").get().getDate().toString());
        assertEquals("2022-01-01T02:00", priceService.findOldestForCrypto("XRP").get().getDate().toString());
    }

    @Test
    void findOldestForUnexpectedCryptoReturnsEmptyOptional() {
        //Expected: findMaxForCrypto for unexpected input returns empty optional
        assertEquals(Optional.empty(), priceService.findOldestForCrypto("BTCq"));
    }
}