package com.docp.product_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name cannot be empty")
        String name,
        String description,

        @Min(value = 10, message = "Product price cannot be below 10")
        BigDecimal price,

        @Min(value = 1, message = "Stock needs to be atleast 1")
        int stock,
        String category,
        String imageUrl) {
}
