package com.nihat.springwebfluxdemo.controllers.fn;

import com.nihat.springwebfluxdemo.model.ProductDTO;
import com.nihat.springwebfluxdemo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {


    private final ProductService productService;
    private final Validator validator;

    private void validate(ProductDTO productDTO){
        Errors errors = new BeanPropertyBindingResult(productDTO, "beerDto");
        validator.validate(productDTO, errors);

        if (errors.hasErrors()){
            throw new ServerWebInputException(errors.toString());
        }
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), ProductDTO.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        return productService.getProductById(request.pathVariable("id"))
                .flatMap(productDTO -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productDTO, ProductDTO.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createProduct(ServerRequest request) {
        return request.bodyToMono(ProductDTO.class)
                .doOnNext(this::validate)
                .flatMap(dto -> productService.saveProduct(Mono.just(dto)))
                .flatMap(productDTO -> ServerResponse
                        .created(UriComponentsBuilder.fromPath("/api/v1/products/{id}")
                                .buildAndExpand(productDTO.getId()).toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(productDTO)); // returning body is optional, location header is enough sometimes.
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        return request.bodyToMono(ProductDTO.class)
                .doOnNext(this::validate)
                .flatMap(productDTO -> productService.updateProduct(request.pathVariable("id"), productDTO))
                .flatMap(productDTO -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(productDTO))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        return productService.deleteProductById(request.pathVariable("id"))
                .then(ServerResponse.noContent().build());
    }
}
