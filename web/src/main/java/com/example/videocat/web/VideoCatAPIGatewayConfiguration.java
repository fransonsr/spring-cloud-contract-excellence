package com.example.videocat.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
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
