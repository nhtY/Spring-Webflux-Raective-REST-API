package com.nihat.springwebfluxdemo.services;

import com.nihat.springwebfluxdemo.model.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDTO> getAllProducts();
    Mono<ProductDTO> getProductById(String id);
    Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTO);
    Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDTO);
    Mono<Void> deleteProductById(String id);
}
