package com.docp.cart_service.service;

import com.docp.cart_service.dto.CartRequest;
import com.docp.cart_service.dto.CartResponse;
import com.docp.cart_service.model.Cart;
import com.docp.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartResponse findById(Long id) {
        Optional<Cart> cartFound = cartRepository.findById(id);
        return cartFound.map(this::mapToCartResponse).orElse(null);

    }

    private CartResponse mapToCartResponse(Cart cart) {
    return  new CartResponse(
        cart.getUserId(), cart.getProductId(), cart.getProductQuantity(), cart.getTotalAmount()
    );
   }


}
