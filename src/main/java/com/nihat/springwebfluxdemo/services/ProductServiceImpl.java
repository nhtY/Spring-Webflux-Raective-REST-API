package com.nihat.springwebfluxdemo.services;

import com.nihat.springwebfluxdemo.mappers.ProductMapper;
import com.nihat.springwebfluxdemo.model.ProductDTO;
import com.nihat.springwebfluxdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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
