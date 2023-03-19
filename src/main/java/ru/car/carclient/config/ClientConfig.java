package ru.car.carclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Slf4j
@Configuration
public class ClientConfig {
    @Value("${path.to.server}")
    private String pathToServer;
    @Bean
    RSocketRequester requester(RSocketRequester.Builder requestBuilder) {
        return requestBuilder.tcp(pathToServer, 7000);
    }
}
