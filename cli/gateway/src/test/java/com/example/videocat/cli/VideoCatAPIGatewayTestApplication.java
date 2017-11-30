package com.example.videocat.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class VideoCatAPIGatewayTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoCatAPIGatewayTestApplication.class, args);
	}
}
