package com.docp.cart_service.dto;

import java.math.BigDecimal;

public record CartResponse(String userId,
                           String productId,
                           Integer productQuantity,
                           BigDecimal totalAmount) {
}
