package com.xm.controller.v1;

import com.xm.controller.v1.dto.RecommendationDto;
import com.xm.service.RecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/recommendation")
@Api(tags = "Recommendation")
public class RecommendationController {

    final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @ApiOperation(value = "Get list of all recommendations.", notes = "Retrieves a descending sorted list of all the cryptos, comparing the normalized range.")
    @GetMapping(params = "sort=DESC", produces = "application/json")
    public ResponseEntity<List<RecommendationDto>> getAll() {
        return ResponseEntity.ok(
                recommendationService.getAllSortByRangeDesc().stream().map(RecommendationDto::new).toList());
    }

    @ApiOperation(value = "Get recommendation by date.", notes = "Retrieves the crypto with the highest normalized range for a specific day.")
    @GetMapping(value = "/{date}", produces = "application/json")
    public ResponseEntity<RecommendationDto> getHighestByDate(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")
            @ApiParam(required = true, value = "Date", example = "2022-01-01") LocalDate date) {
        return recommendationService.getHighestRangeForDate(date).map(RecommendationDto::new).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
