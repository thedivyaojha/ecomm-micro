package com.docp.order_service.service;

import com.docp.order_service.dto.CartResponse;
import com.docp.order_service.dto.OrderItemResponses;
import com.docp.order_service.dto.OrderResponse;
import com.docp.order_service.enums.OrderStatus;
import com.docp.order_service.model.Order;
import com.docp.order_service.model.OrderItem;
import com.docp.order_service.repository.OrderRepository;
import com.docp.order_service.restClient.RestClientProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestClientProvider restClientProvider;
    private final StreamBridge streamBridge;

    /*
     * Creates order from user's cart.
     * Steps:
     * 1. Fetch cart items from Cart Service
     * 2. Create Order with PENDING status
     * 3. Clear cart after successful order
     */

    public OrderResponse createOrder(Long userId){

        // 1. Fetch cart items
        List<CartResponse>cartItems= restClientProvider.getCartByUserId(userId);

        if(cartItems.isEmpty()){
            throw new RuntimeException("Cart is empty, cannot create order");
        }

        // 2. Create Order entity
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PENDING);


        // Convert cart items to order items
        List<OrderItem> orderItems = cartItems.stream()
                .map(cart-> new OrderItem(
                        cart.productId(),
                        cart.productQuantity(),
                        cart.totalAmount().divide(BigDecimal.valueOf(cart.productQuantity())) // unir price
                ))
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        order.setOrderItems(orderItems);

        // calculating total
        BigDecimal total =cartItems.stream()
                .map(CartResponse::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);

        //save order
        Order savedOrder = orderRepository.save(order);
        log.info("Order created: {}", savedOrder.getId());

        // 3. Clear cart
        boolean cleared = restClientProvider.clearCart(userId);
        if(!cleared){
            log.warn("Failed to clear cart for user: {}", userId);
        }

        streamBridge.send("createOrder-out-0" , Map.of("order-id", savedOrder.getId(), "status", "CREATED"));
        return mapToOrderResponse(savedOrder);

    }


    // will make custom method mapTpOrderResponse now
    private OrderResponse mapToOrderResponse(Order order){
        List<OrderItemResponses> items = order.getOrderItems().stream()
                .map(item->new OrderItemResponses(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getTotalAmount(),
                items,
                order.getCreatedAt()
        );

    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for user: " + userId);
        }

        return orders.stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    /*
     * Get order by ID
     */
    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        return mapToOrderResponse(order);
    }

    /*
     * Update order status
     */
    public OrderResponse updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        log.info("Order {} status updated to {}", orderId, status);
        return mapToOrderResponse(updatedOrder);
    }
}
