package com.docp.order_service.repository;


import com.docp.order_service.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRespository  extends JpaRepository<OrderItem,Long> {
}
