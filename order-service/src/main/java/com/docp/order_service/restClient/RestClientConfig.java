package com.docp.order_service.restClient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced   //Enables Eureka service discovery + load balancing
    @Qualifier("cartRestClient")
    public RestClient cartRestClientConfig(RestClient.Builder clientBuilder) {
        return clientBuilder.baseUrl("http://localhost:8083").build();
    }
}
