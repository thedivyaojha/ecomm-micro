package com.docp.cart_service.service;

import com.docp.cart_service.dto.CartResponse;
import com.docp.cart_service.dto.ProductDto;
import com.docp.cart_service.model.Cart;
import com.docp.cart_service.repository.CartRepository;
import com.docp.cart_service.restclient.RestClientProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final RestClientProvider restClientProvider;

    public CartResponse findById(Long id) {
        Optional<Cart> cartFound = cartRepository.findById(id);
        return cartFound.map(this::mapToCartResponse).orElse(null);

    }

    private CartResponse mapToCartResponse(Cart cart) {
        return new CartResponse(
                cart.getUserId(), cart.getProductId(), cart.getProductQuantity(), cart.getTotalAmount()
        );
    }

    //    public void clearCart(Long userId) {
//        cartRepository.deleteByUserId(userId);
//    }
    public boolean clearCart(Long userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (cartOptional.isPresent()) {
            cartRepository.delete(cartOptional.get());
            return true;
        }

        return false;
    }

    //inter service communication
    public boolean addProductToCart(Long productId, Long userId) {

        //verify user exists or not
        boolean userValidation = restClientProvider.validateUser(userId);
        if (!userValidation) {
            return false;
        }

        //verify ye product exist karti bi  hai ya ni
        Optional<ProductDto> product = restClientProvider.getProductById(productId);
        if (product.isEmpty()) {
            return false;
        }
        log.info("Product details  : {} ", product.get());
//
//        // if it reaches here means both validated
        Cart usersCart = cartRepository.findCartByUserId(userId);
        if (usersCart != null) {
            log.info("Users cart has been updated with new values");
            usersCart.setProductQuantity(usersCart.getProductQuantity() + 1);
            usersCart.setTotalAmount(usersCart.getTotalAmount().add(product.get().price()));
            log.info("now saving product quantity and total amt in cart repo, yeyee");
            cartRepository.save(usersCart);
            return true;
        }

        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setProductId(productId);
        newCart.setProductQuantity(1);
        newCart.setTotalAmount(product.get().price());
        cartRepository.save(newCart);
        log.info("New cart : {} ", newCart);
        return true;
    }

    public boolean removeProductFromCart(Long productId, Long userId) {
        boolean userValidation = restClientProvider.validateUser(userId);
        if (!userValidation) {
            return false;
        }
        Optional<ProductDto> product = restClientProvider.getProductById(productId);
        if (!product.isEmpty()) {
            log.info("Product details  : {} ", product.get());
            Cart usersCart = cartRepository.findCartByUserId(userId);
            if (usersCart != null) {
                usersCart.setProductQuantity(usersCart.getProductQuantity() - 1);
                usersCart.setTotalAmount(usersCart.getTotalAmount().subtract(product.get().price()));
                cartRepository.save(usersCart);
                return true;
            }


        }

        return false;


    }


}
