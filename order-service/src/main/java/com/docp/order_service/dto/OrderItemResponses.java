package com.docp.order_service.dto;

import java.math.BigDecimal;

public record OrderItemResponses(
       Long productId, Integer quantity, BigDecimal price
) { }
