package com.nihat.springwebfluxdemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private String id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotBlank
    @URL
    private String imgUrl;

    @NotNull
    private BigDecimal price;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
