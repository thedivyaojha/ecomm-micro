package com.docp.order_service.controller;
import com.docp.order_service.model.Order;
import com.docp.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/health")
    public String getHealth() {
        return "Order Service is up and running!";
    }


}
