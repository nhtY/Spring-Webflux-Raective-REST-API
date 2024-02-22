package com.nihat.springwebfluxdemo.controllers.mvc;

import com.nihat.springwebfluxdemo.model.ProductDTO;
import com.nihat.springwebfluxdemo.services.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProductService productService;

    @Test
    @Order(1)
    void testGetAllProducts() {
        // When
        webTestClient.get().uri(ProductController.PRODUCT_BASE_URL)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ProductDTO.class).hasSize(4); // initially loaded + testProduct
    }

    @Test
    @Order(2)
    void testGetProductByIdSuccess() {
        // Given
        String productId = getSavedTestProduct().getId();

        // When
        webTestClient.get().uri(ProductController.PRODUCT_ID_URL, productId)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductDTO.class);
    }

    @Test
    @Order(3)
    void testGetProductByIdNotFound() {
        // Given
        String nonExistentProductId = "non_existent_id";

        // When
        webTestClient.get().uri(ProductController.PRODUCT_ID_URL, nonExistentProductId)
                .exchange()
                // Then
                .expectStatus().isNotFound();
    }


    private ProductDTO getSavedTestProduct() {
        return productService.getAllProducts().next().block();
    }


}