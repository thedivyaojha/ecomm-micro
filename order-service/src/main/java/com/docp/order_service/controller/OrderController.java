package com.docp.order_service.controller;
import com.docp.order_service.dto.OrderResponse;
import com.docp.order_service.enums.OrderStatus;
import com.docp.order_service.model.Order;
import com.docp.order_service.repository.OrderRepository;
import com.docp.order_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/health")
    public String getHealth() {
        return "Order Service is up and running!";
    }

    /*
     * Create order from user's cart.
     * POST /api/v1/order?userId=5
     */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestParam("userId") Long userId) {
        OrderResponse order = orderService.createOrder(userId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);

        //http://localhost:8084/api/v1/order?userId=5
    }

    /*
     * Get all orders for a user
     * GET /api/v1/order/user?userId=5
     */
    @GetMapping("/user")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@RequestParam Long userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }


    /*
     * Get order by ID
     * GET /api/v1/order/{orderId}
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String orderId) {
        OrderResponse order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }


    /*
     * Update order status
     * PUT /api/v1/order/{orderId}/status?status=CONFIRMED
     */
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable String orderId , @RequestParam OrderStatus status
    ){
        OrderResponse order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }

    // OrderController - add this
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        log.error("Error: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
