package com.docp.cart_service.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record CartRequest(
         String userId,
         String productId,
         Integer productQuantity,
         BigDecimal totalAmount) {
}
