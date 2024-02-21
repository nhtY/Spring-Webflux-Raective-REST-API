package com.nihat.springwebfluxdemo.repositories;

import com.nihat.springwebfluxdemo.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
