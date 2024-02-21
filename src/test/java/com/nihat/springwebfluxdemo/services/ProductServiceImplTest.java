package com.nihat.springwebfluxdemo.services;

import com.nihat.springwebfluxdemo.domain.Product;
import com.nihat.springwebfluxdemo.model.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    void getAllProducts() {
        // test the getAllProducts method using step verifier, expect to have 4 products
        Flux<ProductDTO> result = productService.getAllProducts();

        // Then
        StepVerifier.create(result)
                .expectNextCount(4) // Assuming we have 4 products in our database
                .verifyComplete();

    }

    @Test
    void testFindById() {

        String productId = "65d6028bdd9e7454d348cac2";
        String expectedProductName = "Iphone 12";
        BigDecimal expectedPrice = BigDecimal.valueOf(28999.99);

        // When
        Mono<ProductDTO> resultMono = productService.getProductById(productId);

        // Then
        StepVerifier.create(resultMono)
                .expectNextMatches(dto -> dto.getId().equals(productId)
                        && dto.getName().equals(expectedProductName)
                        && Objects.equals(dto.getPrice(), expectedPrice))
                .verifyComplete();
    }

    @Test
    void testFindByIdNotFound() {

        String productId = "XX";

        // When
        Mono<ProductDTO> resultMono = productService.getProductById(productId);

        // Then
        StepVerifier.create(resultMono)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testSave() {

        // When
        Mono<ProductDTO> resultMono = productService.saveProduct(Mono.just(getTestProductDto()));

        // Then
        StepVerifier.create(resultMono)
                .expectNextMatches(dto -> dto != null && dto.getId() != null)
                .verifyComplete();
    }

    private static ProductDTO getTestProductDto() {
        return ProductDTO.builder()
                .name("Test Product")
                .description("Test description")
                .imgUrl("https://test.com/testImage.jpeg")
                .price(BigDecimal.TEN)
                .build();
    }

}