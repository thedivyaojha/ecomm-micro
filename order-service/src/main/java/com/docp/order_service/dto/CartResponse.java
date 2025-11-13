package com.docp.order_service.dto;

import java.math.BigDecimal;

public record CartResponse(
        Long userId, Long productId, Integer productQuantity, BigDecimal totalAmount
) { }
