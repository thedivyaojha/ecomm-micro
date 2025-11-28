package com.docp.notification_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

@Service
@Slf4j
public class OrderEventConsumer {
    @Bean
    public Consumer<Map<String,Object>>  orderCreated() {
        return order ->
        {
            log.info("received order: {}", order);

        };
    }

}
