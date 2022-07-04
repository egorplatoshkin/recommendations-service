package com.xm.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xm.models.Price;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "dateTime",
        "symbol",
        "price"
})
@ApiModel(value = "Price")
public class PriceDto {
    @ApiModelProperty(value = "Price created on.", example = "2022-01-02 02:00:00")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateTime;
    @ApiModelProperty(value = "Short name of the crypto.", example = "BTC")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String symbol;
    @ApiModelProperty(value = "Price of the crypto.", example = "47722.66")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double price;

    public PriceDto(Price other) {
        this.dateTime = other.getDate();
        this.symbol = other.getSymbol();
        this.price = other.getPrice();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public PriceDto setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public PriceDto setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PriceDto setPrice(double price) {
        this.price = price;
        return this;
    }
}
