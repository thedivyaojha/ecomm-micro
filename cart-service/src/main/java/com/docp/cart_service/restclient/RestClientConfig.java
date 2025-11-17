package com.docp.cart_service.restclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @LoadBalanced // this resolves the urls we used below
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
        return clientBuilder.baseUrl("http://USER-SERVICE").build(); //
    }

}
