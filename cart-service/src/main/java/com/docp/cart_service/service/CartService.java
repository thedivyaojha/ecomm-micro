package com.docp.cart_service.service;

import com.docp.cart_service.dto.CartResponse;
import com.docp.cart_service.dto.ProductDto;
import com.docp.cart_service.model.Cart;
import com.docp.cart_service.repository.CartRepository;
import com.docp.cart_service.restclient.RestClientProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final RestClientProvider restClientProvider;

    public CartResponse findById(Long id) {
        Optional<Cart> cartFound = cartRepository.findById(id);
        return cartFound.map(this::mapToCartResponse).orElse(null);

    }

    private CartResponse mapToCartResponse(Cart cart) {
    return  new CartResponse(
        cart.getUserId(), cart.getProductId(), cart.getProductQuantity(), cart.getTotalAmount()
    );
   }


   //inter service communication
    public boolean addProductToCart(Long productId, Long userId) {
        //verify ye product exist karti bi  hai ya ni
        Optional<ProductDto> product= restClientProvider.getProductById(productId);
        if(product.isEmpty()) {
            log.info("product not found");
            return false;
        }

        return true;


        //verify user exists or not
    }
}
