package com.nihat.springwebfluxdemo.services;

import com.nihat.springwebfluxdemo.model.ProductDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Flux<ProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public Mono<ProductDTO> getProductById(String id) {
        return null;
    }

    @Override
    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTO) {
        return null;
    }

    @Override
    public Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDTO) {
        return null;
    }

    @Override
    public Mono<Void> deleteProductById(String id) {
        return null;
    }
}
