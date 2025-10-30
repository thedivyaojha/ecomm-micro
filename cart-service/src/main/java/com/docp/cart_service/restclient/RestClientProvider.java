package com.docp.cart_service.restclient;
import com.docp.cart_service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class RestClientProvider {

    private final RestClient productRestClient;
    private final RestClient userRestClient;


    public RestClientProvider(@Qualifier("productRestClient")  RestClient productRestClient, @Qualifier("userRestClient") RestClient userRestClient) {
        this.productRestClient = productRestClient;
        this.userRestClient = userRestClient;
    }
    public boolean validateUser(Long userId) {
        return Boolean.TRUE.equals(userRestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/user/validate/{id}").build(userId))
                .retrieve()
                .body(Boolean.class));
    }


    public Optional<ProductDto> getProductById( Long productId )
    {
        ProductDto productDto = productRestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/product/{id}").build(productId))
                .retrieve()
                .body(ProductDto.class);

        return Optional.ofNullable(productDto);
    }
}
