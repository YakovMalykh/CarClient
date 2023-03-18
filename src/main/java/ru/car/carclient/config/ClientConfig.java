package ru.car.carclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Slf4j
@Configuration
public class ClientConfig {
    @Bean
    RSocketRequester requester(RSocketRequester.Builder requestBuilder) {
        return requestBuilder.tcp("localhost", 7000);
    }
}
