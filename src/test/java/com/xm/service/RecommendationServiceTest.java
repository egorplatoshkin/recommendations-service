package com.xm.service;

import com.xm.models.Recommendation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RecommendationServiceTest {
    @Autowired
    RecommendationService recommendationService;

    @Test
    void getAllSortByDesc() {
        //When: Get all cryptos sorted descending by normalized range
        List<Recommendation> allSortByRangeDesc = recommendationService.getAllSortByRangeDesc();
        //Then:
        assertEquals("ETH", allSortByRangeDesc.get(0).getCrypto());
        assertEquals(0.6383810110763016, allSortByRangeDesc.get(0).getNormalizedRange());
        assertEquals("XRP", allSortByRangeDesc.get(1).getCrypto());
        assertEquals(0.5060541310541311, allSortByRangeDesc.get(1).getNormalizedRange());
        assertEquals("DOGE", allSortByRangeDesc.get(2).getCrypto());
        assertEquals(0.5046511627906975, allSortByRangeDesc.get(2).getNormalizedRange());
        assertEquals("LTC", allSortByRangeDesc.get(3).getCrypto());
        assertEquals(0.4651837524177949, allSortByRangeDesc.get(3).getNormalizedRange());
        assertEquals("BTC", allSortByRangeDesc.get(4).getCrypto());
        assertEquals(0.43412110435594536, allSortByRangeDesc.get(4).getNormalizedRange());
    }

    @Test
    void getHighestNormalizedRangeForDateWorks() {
        //Expected: works for January
        assertEquals("XRP", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 1)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 2)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 3)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 4)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 5)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 6)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 7)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 8)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 9)).get().getCrypto());
        assertEquals("XRP", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 10)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 11)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 12)).get().getCrypto());
        assertEquals("XRP", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 13)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 14)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 15)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 16)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 17)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 18)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 19)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 20)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 21)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 22)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 23)).get().getCrypto());
        assertEquals("BTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 24)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 25)).get().getCrypto());
        assertEquals("DOGE", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 26)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 27)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 28)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 29)).get().getCrypto());
        assertEquals("LTC", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 30)).get().getCrypto());
        assertEquals("ETH", recommendationService.getHighestRangeForDate(LocalDate.of(2022,1, 31)).get().getCrypto());
    }

    @Test
    void getHighestNormalizedRangeForUnexpectedDateReturnEmptyOptional() {
        //Expected: return empty optional
        assertEquals(Optional.empty(), recommendationService.getHighestRangeForDate(LocalDate.of(2022,2, 1)));
    }
}