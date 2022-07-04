package com.xm.service;

import com.xm.models.Recommendation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecommendationService {

    List<Recommendation> getAllSortByRangeDesc();
    Optional<Recommendation> getHighestRangeForDate(LocalDate date);
}
