package com.docp.cart_service.dto;

import java.math.BigDecimal;

public record ProductDto(String name, String description, BigDecimal price, String imageUrl, String category) {
}
