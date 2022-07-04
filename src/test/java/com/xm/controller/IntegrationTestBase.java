package com.xm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTestBase {
    private final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    private TestRestTemplate autoconfiguredTestRestTemplate;
    protected TestRestTemplate rest;

    @BeforeEach
    void setUp() {
        rest = new TestRestTemplate(new RestTemplateBuilder().rootUri(autoconfiguredTestRestTemplate.getRootUri()));
    }

    protected JsonNode assertOnHttpResponse(ResponseEntity<String> response, Consumer<ResponseEntity> httpAssertions,
                                            Consumer<JsonNode> bodyAssertions) {

        httpAssertions.accept(response);
        try {
            JsonNode body = Objects.nonNull(response.getBody()) ? mapper.readTree(response.getBody()) : null;
            bodyAssertions.accept(body);
            return body;
        } catch (IOException e) {
            Fail.fail("Response body was not valid JSON: %s", response.getBody());
            return null;
        }
    }

    protected void assertStatus(ResponseEntity response, HttpStatus status) {
        assertThat(response.getStatusCode()).as("Expected %s response but got %s: %s", status, response.getStatusCode(),
                response.getBody()).isEqualTo(status);
    }

}
