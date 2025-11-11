package com.docp.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * OrderItem is NOT a separate collection in MongoDB.
 * It's embedded inside Order document as an array.
 * No @Document annotation needed - just a POJO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;


}
