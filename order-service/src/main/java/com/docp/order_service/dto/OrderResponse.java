package com.docp.order_service.dto;

import com.docp.order_service.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        Long userid,
        OrderStatus status,
        BigDecimal totalAmount,
        List<OrderItemResponses> orderItems,
        LocalDateTime createdAt
) {
}
