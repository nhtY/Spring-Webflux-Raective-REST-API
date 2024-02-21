package com.nihat.springwebfluxdemo.mappers;

import com.nihat.springwebfluxdemo.domain.Product;
import com.nihat.springwebfluxdemo.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
     ProductDTO toProductDTO(Product product);
     Product toProduct(ProductDTO productDTO);
}
