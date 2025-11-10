package com.docp.cart_service.repository;

import com.docp.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Long cartId(Long cartId);


    void deleteByUserId(Long userId);

    List<Cart> findByUserId(Long userId);

    Cart findCartByUserId(Long userId);

}




