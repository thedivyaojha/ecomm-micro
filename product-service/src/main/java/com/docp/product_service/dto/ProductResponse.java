package com.docp.product_service.dto;

import java.math.BigDecimal;

public record ProductResponse(String name, String description, BigDecimal price, String imageUrl, String category) {
}
