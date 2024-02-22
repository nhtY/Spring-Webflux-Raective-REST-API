package com.nihat.springwebfluxdemo.controllers.mvc;

import com.nihat.springwebfluxdemo.model.ProductDTO;
import com.nihat.springwebfluxdemo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductController {

    public static final String PRODUCT_BASE_URL = "/api/v1/products";
    public static final String PRODUCT_ID_URL = PRODUCT_BASE_URL + "/{id}";

    private final ProductService productService;

    // handle get all products:
    @GetMapping(PRODUCT_BASE_URL)
    public Flux<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    // handle get product by id:
    @GetMapping(PRODUCT_ID_URL)
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // handle create product that returns the created mono and location header:
    @PostMapping(PRODUCT_BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDTO> createProduct(@RequestBody @Validated Mono<ProductDTO> productDTOMono) {
        return productService.saveProduct(productDTOMono);
    }

    // handle update product:
    @PutMapping(PRODUCT_ID_URL)
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody  @Validated ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // handle delete product:
    @DeleteMapping(PRODUCT_ID_URL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProductById(id);
    }

}
