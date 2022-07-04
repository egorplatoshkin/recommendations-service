package com.xm.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xm.models.Recommendation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "cryptoName",
        "normalizedRange"
})
@ApiModel(value = "Recommendation")
public class RecommendationDto {
    @ApiModelProperty(value = "Short name of the crypto.", example = "BTC")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String cryptoName;
    @ApiModelProperty(value = "Normalized Range of the crypto (max-min)/min", example = "0.5046511627906975")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double normalizedRange;

    public RecommendationDto(Recommendation other) {
        this.cryptoName = other.getCrypto();
        this.normalizedRange = other.getNormalizedRange();
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public RecommendationDto setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
        return this;
    }

    public Double getNormalizedRange() {
        return normalizedRange;
    }

    public RecommendationDto setNormalizedRange(Double normalizedRange) {
        this.normalizedRange = normalizedRange;
        return this;
    }
}
