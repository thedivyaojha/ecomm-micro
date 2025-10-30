package com.docp.cart_service.repository;

import com.docp.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Long cartId(Long cartId);

    Cart findCartByUserId(Long userId);

    void deleteByUserId(Long userId);
    
}




