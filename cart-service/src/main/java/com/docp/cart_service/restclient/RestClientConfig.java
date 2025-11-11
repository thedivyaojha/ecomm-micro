package com.docp.cart_service.restclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    @Qualifier("productRestClient")
    public RestClient productRestClientConfig(RestClient.Builder clientBuilder) {
        return clientBuilder.baseUrl("http://PRODUCT-SERVICE").build();
    }

    @Bean
    @Qualifier("userRestClient")
    public RestClient userRestClientConfig(RestClient.Builder clientBuilder) {
        return clientBuilder.baseUrl("http://USER-SERVICE").build();
    }

}
