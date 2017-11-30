package com.example.videocat.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class VideoCatAPIGateway {

	private static final Logger LOG = LoggerFactory.getLogger(VideoCatAPIGateway.class);

	private RestTemplate restTemplate;

	public VideoCatAPIGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Video> list() {
		ParameterizedTypeReference<List<Video>> ptr = new ParameterizedTypeReference<List<Video>>() {
		};

		LOG.error("Requesting videos");

		ResponseEntity<List<Video>> response = restTemplate.exchange("http://localhost:8080/videos", HttpMethod.GET,
				null, ptr);
		return response.getBody();
	}

}
