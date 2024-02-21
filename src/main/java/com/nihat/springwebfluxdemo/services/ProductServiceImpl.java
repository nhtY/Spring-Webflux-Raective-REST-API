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
        return productRepository.findAll()
                .map(productMapper::toProductDTO);
    }

    @Override
    public Mono<ProductDTO> getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO);
    }

    @Override
    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTO) {
        return productDTO.map(productMapper::toProduct)
                .flatMap(productRepository::save)
                .map(productMapper::toProductDTO);
    }

    @Override
    public Mono<ProductDTO> updateProduct(String id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Not found")))
                .map(found -> {
                    found.setName(productDTO.getName());
                    found.setDescription(productDTO.getDescription());
                    found.setImgUrl(productDTO.getImgUrl());
                    found.setPrice(productDTO.getPrice());
                    return found;
                }).flatMap(productRepository::save)
                .map(productMapper::toProductDTO);
    }

    @Override
    public Mono<Void> deleteProductById(String id) {
        return null;
    }
}
