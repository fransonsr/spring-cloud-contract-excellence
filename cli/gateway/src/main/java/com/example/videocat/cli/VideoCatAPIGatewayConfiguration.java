package com.example.videocat.cli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableRetry
public class VideoCatAPIGatewayConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public VideoCatAPIGateway gateway(RestTemplate restTemplate) {
        return new VideoCatAPIGateway(restTemplate);
    }
}
