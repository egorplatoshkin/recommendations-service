package com.xm.controller.v1;

import com.xm.controller.v1.dto.PriceDto;
import com.xm.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/price")
@Api(tags = "Price")
public class PriceController {

    public final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @ApiOperation(value = "Get max price by crypto.",
            notes = "Retrieves the max price for a requested crypto.")
    @GetMapping(value = "/{crypto}/max", produces = "application/json")
    public ResponseEntity<PriceDto> getMax(@ApiParam(required = true, value = "Short name of the crypto.")@PathVariable("crypto")
                                               String crypto) {
        return priceService.findMaxForCrypto(crypto).map(PriceDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get min price by crypto.", notes = "Retrieves the min price for a requested crypto.")
    @GetMapping(value = "/{crypto}/min", produces = "application/json")
    public ResponseEntity<PriceDto> getMin(@ApiParam(required = true, value = "Short name of the crypto.")@PathVariable("crypto")
                                           String crypto) {
        return priceService.findMinForCrypto(crypto).map(PriceDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get newest price by crypto.", notes = "Retrieves the newest price for a requested crypto.")
    @GetMapping(value = "/{crypto}/newest", produces = "application/json")
    public ResponseEntity<PriceDto> getNewest(@ApiParam(required = true, value = "Short name of the crypto.")@PathVariable("crypto")
                                           String crypto) {
        return priceService.findNewestForCrypto(crypto).map(PriceDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get oldest price by crypto.", notes = "Retrieves the oldest price for a requested crypto.")
    @GetMapping(value = "/{crypto}/oldest", produces = "application/json")
    public ResponseEntity<PriceDto> getOldest(@ApiParam(required = true, value = "Short name of the crypto.")@PathVariable("crypto")
                                              String crypto) {
        return priceService.findOldestForCrypto(crypto).map(PriceDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
