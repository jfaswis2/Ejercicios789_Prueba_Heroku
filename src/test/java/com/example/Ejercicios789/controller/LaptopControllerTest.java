package com.example.Ejercicios789.controller;

import com.example.Ejercicios789.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    private ResponseEntity<Laptop> response;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "ram": "4gb",
                    "cpu": "Ryzen 5",
                    "storage": "1tb"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        response = testRestTemplate.exchange("/api/laptops",
                HttpMethod.POST,
                request,
                Laptop.class);
    }

    @DisplayName("Comprobar findAll desde controladores Spring Rest")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @DisplayName("Comprobar findOneById desde controladores Spring Rest")
    @Test
    void findOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Comprobar create desde controladores Spring Rest")
    @Test
    void create() {

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("4gb", result.getRam());
    }

    /*
    @DisplayName("Comprobar update desde controladores Spring Rest")
    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": "1",
                    "ram": "8gb",
                    "cpu": "Ryzen 7",
                    "storage": "2tb"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        response = testRestTemplate.exchange("/api/laptops",
                HttpMethod.PUT,
                request,
                Laptop.class);

        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("8gb", result.getRam());
        assertEquals("Ryzen 7", result.getCpu());
        assertEquals("2tb", result.getStorage());
    }*/

    @DisplayName("Comprobar delete desde controladores Spring Rest")
    @Test
    void delete() {

        ResponseEntity<Void> response = testRestTemplate.exchange("/api/laptops/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, Void.class);

        assertEquals(204, response.getStatusCode().value());
    }

    @DisplayName("Comprobar deleteAll desde controladores Spring Rest")
    @Test
    void deleteAll() {
        ResponseEntity<Void> response = testRestTemplate.exchange("/api/laptops",
                HttpMethod.DELETE,
                HttpEntity.EMPTY, Void.class);

        assertEquals(204, response.getStatusCode().value());
    }
}