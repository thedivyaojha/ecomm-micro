package com.docp.cart_service.controller;

import com.docp.cart_service.dto.CartResponse;
import com.docp.cart_service.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
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

//    @DeleteMapping
//    public ResponseEntity<String> removeFromCart(@RequestParam Long userId) {
//        try{
//            cartService.clearCart(userId);
//            return ResponseEntity.ok("Removed from cart ");
//        }
//        catch(Exception e){
//            return new ResponseEntity<>("Couldn't find the cart  ", HttpStatus.NOT_FOUND);
//        }
//
//    }
@DeleteMapping
public ResponseEntity<String> removeFromCart(@RequestParam Long userId) {
    try {
        boolean wasCleared = cartService.clearCart(userId);

        if (wasCleared) {
            return ResponseEntity.ok("Removed from cart");
        } else {
            return new ResponseEntity<>("Cart not found or already empty", HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>("Error clearing cart: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}
