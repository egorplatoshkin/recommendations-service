package com.xm.controller.v1;

import com.xm.controller.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PriceControllerTest extends IntegrationTestBase {

    @Test
    void getMaxPriceByCrypto() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/btc/max", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then:
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.OK),
                body -> {
                    assertThat(body.isArray()).isFalse();
                    assertThat(body.toString()).isNotNull();
                    String dateTime = body.get("dateTime").textValue();
                    String symbol = body.get("symbol").textValue();
                    Double price = body.get("price").doubleValue();
                    assertThat(dateTime).isEqualTo("2022-01-02 02:00:00");
                    assertThat(symbol).isEqualTo("BTC");
                    assertThat(price).isEqualTo(47722.66);
                });
    }

    @Test
    void getMaxPriceByCryptoNotFoundIfEntityAbsentForCriteria() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/btcctb/max", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: nothing retrieved with NOT_FOUND status
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.NOT_FOUND),
                body -> assertThat(body).isNull());
    }

    @Test
    void getMinPriceByCrypto() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/DOGE/min", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then:
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.OK),
                body -> {
                    assertThat(body.isArray()).isFalse();
                    assertThat(body.toString()).isNotNull();
                    String dateTime = body.get("dateTime").textValue();
                    String symbol = body.get("symbol").textValue();
                    Double price = body.get("price").doubleValue();
                    assertThat(dateTime).isEqualTo("2022-01-22 13:00:00");
                    assertThat(symbol).isEqualTo("DOGE");
                    assertThat(price).isEqualTo(0.129);
                });
    }

    @Test
    void getMinPriceByCryptoNotFoundIfEntityAbsentForCriteria() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/DOG/min", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: nothing retrieved with NOT_FOUND status
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.NOT_FOUND),
                body -> assertThat(body).isNull());
    }

    @Test
    void getNewestPriceByCrypto() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/Eth/newest", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then:
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.OK),
                body -> {
                    assertThat(body.isArray()).isFalse();
                    assertThat(body.toString()).isNotNull();
                    String dateTime = body.get("dateTime").textValue();
                    String symbol = body.get("symbol").textValue();
                    Double price = body.get("price").doubleValue();
                    assertThat(dateTime).isEqualTo("2022-01-31 22:00:00");
                    assertThat(symbol).isEqualTo("ETH");
                    assertThat(price).isEqualTo(2672.5);
                });
    }

    @Test
    void getNewestPriceByCryptoNotFoundIfEntityAbsentForCriteria() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/USD/newest", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: nothing retrieved with NOT_FOUND status
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.NOT_FOUND),
                body -> assertThat(body).isNull());
    }

    @Test
    void getOldestPriceByCrypto() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/LTc/oldest", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then:
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.OK),
                body -> {
                    assertThat(body.isArray()).isFalse();
                    assertThat(body.toString()).isNotNull();
                    String dateTime = body.get("dateTime").textValue();
                    String symbol = body.get("symbol").textValue();
                    Double price = body.get("price").doubleValue();
                    assertThat(dateTime).isEqualTo("2022-01-01 08:00:00");
                    assertThat(symbol).isEqualTo("LTC");
                    assertThat(price).isEqualTo(148.1);
                });
    }

    @Test
    void getOldestPriceByCryptoNotFoundIfEntityAbsentForCriteria() {
        //When: get max price by specific crypto
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/crypto/oldest", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: nothing retrieved with NOT_FOUND status
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.NOT_FOUND),
                body -> assertThat(body).isNull());
    }

    @Test
    void getByCryptoRedirectsToSwaggerOnError() {
        //When: url path is broken
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/price/BTC", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: redirect to Swagger UI
        assertStatus(responseEntity, HttpStatus.OK);
        assertTrue(MediaType.TEXT_HTML.isCompatibleWith(responseEntity.getHeaders().getContentType()));
        assertThat(responseEntity.getBody()).contains("Swagger UI");
    }
}