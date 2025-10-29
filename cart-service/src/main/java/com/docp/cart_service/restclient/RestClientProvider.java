package com.docp.cart_service.restclient;
import com.docp.cart_service.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class RestClientProvider {

    private final RestClient productRestClient;


    public RestClientProvider(RestClient productRestClient) {
        this.productRestClient = productRestClient;
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
