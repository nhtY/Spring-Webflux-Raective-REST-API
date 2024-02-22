package com.nihat.springwebfluxdemo.controllers.fn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ProductRouterConfig {

    public static final String PRODUCT_BASE_PATH = "/api/v2/products";
    public static final String PRODUCT_ID_PATH = PRODUCT_BASE_PATH + "/{id}";

    private final ProductHandler productHandler;

    @Bean
    public RouterFunction<ServerResponse> productRouter() {
        return route()
                .GET(PRODUCT_BASE_PATH, productHandler::getAllProducts)
                .GET(PRODUCT_ID_PATH, productHandler::getProductById)
                .POST(PRODUCT_BASE_PATH, productHandler::createProduct)
                .PUT(PRODUCT_ID_PATH, productHandler::updateProduct)
                .DELETE(PRODUCT_ID_PATH, productHandler::deleteProduct)
                .build();
    }
}
