package com.nihat.springwebfluxdemo.services;

import com.nihat.springwebfluxdemo.model.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

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

}