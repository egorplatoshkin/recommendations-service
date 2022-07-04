package com.xm.controller.v1;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.xm.controller.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommendationControllerTest extends IntegrationTestBase {
    @Test
    void getAll() {
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/recommendation?sort=DESC", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.OK),
                body -> {
                    assertThat(body.isArray()).isTrue();
                    assertThat(body.toString()).isNotNull();
                    ArrayNode contentArray = (ArrayNode) body;
                    assertThat(contentArray.size()).isEqualTo(5);
                });
    }

    @Test
    void getAllRedirectsToSwaggerOnError() {
        //When: url path is broken
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/recommendation?sort=BTC", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: redirect to Swagger UI
        assertStatus(responseEntity, HttpStatus.OK);
        assertTrue(MediaType.TEXT_HTML.isCompatibleWith(responseEntity.getHeaders().getContentType()));
        assertThat(responseEntity.getBody()).contains("Swagger UI");
    }

    @Test
    void getHighestByDateWorks() {
        //When: requested to the highest normalized range by date
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/recommendation/2022-01-01", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: retrieved single value
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.OK),
                body -> {
                    assertThat(body.isArray()).isFalse();
                    assertThat(body.toString()).isNotNull();
                    String cryptoName = body.findValue("cryptoName").textValue();
                    Double normalizedRange = body.findValue("normalizedRange").doubleValue();
                    assertThat(cryptoName).isEqualTo("XRP");
                    assertThat(normalizedRange).isEqualTo(0.019281754639672227);
                });
    }

    @Test
    void getHighestByDateNotFoundIfEntityAbsentForCriteria() {
        //When: requested to the highest normalized range by unexpected date
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/recommendation/2022-02-01", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: nothing retrieved with NOT_FOUND status
        assertOnHttpResponse(
                responseEntity,
                httpResponse -> assertStatus(httpResponse, HttpStatus.NOT_FOUND),
                body -> assertThat(body).isNull());
    }

    @Test
    void getHighestByDateRedirectsToSwaggerOnError() {
        //When: url path is broken
        ResponseEntity<String> responseEntity =
                rest.exchange("/api/v1/recommendation/2022-01-", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        //Then: Redirect to Swagger UI
        assertStatus(responseEntity, HttpStatus.OK);
        assertTrue(MediaType.TEXT_HTML.isCompatibleWith(responseEntity.getHeaders().getContentType()));
        assertThat(responseEntity.getBody()).contains("Swagger UI");
    }

}