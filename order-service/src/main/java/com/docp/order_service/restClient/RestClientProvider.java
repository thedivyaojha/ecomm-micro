package com.docp.order_service.restClient;

import com.docp.order_service.dto.CartResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class RestClientProvider {
    private final RestClient cartRestClient;

    //this is constructor of rest client provider
    public RestClientProvider(@Qualifier( "cartRestClient") RestClient cartRestClient){
        this.cartRestClient = cartRestClient;
    }

    /**
     * Fetches all cart items for a user from Cart Service.
     * Calls: GET /api/v1/cart/user/{userId}
     */
    public List<CartResponse> getCartByUserId(Long userId){
        return cartRestClient.get()
                .uri("/api/v1/cart/user/{userId}", userId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    /**
     * Clears cart after order is placed.
     * Calls: DELETE /api/v1/cart?userId={userId}
     */
    public boolean clearCart(Long userId){
        String response = cartRestClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart")
                        .queryParam("userId",userId)
                        .build()
                )
                .retrieve()
                .body(String.class);
        return response != null && response.contains("Removed");
    }



}
