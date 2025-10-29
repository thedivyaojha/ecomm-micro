package com.docp.cart_service.controller;

import com.docp.cart_service.dto.CartResponse;
import com.docp.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long id) {
        return new ResponseEntity(cartService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestParam Long productId, @RequestParam Long userId) {
        cartService.addProductToCart(productId, userId);
        return new ResponseEntity<>("Added to cart ", HttpStatus.CREATED);
    }


}
