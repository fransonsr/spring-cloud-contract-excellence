package com.example.videocat.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.videocat.cli.exception.NotFoundException;
import com.example.videocat.cli.exception.ServiceUnavailableException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class VideoCatAPIGateway {

	private RestTemplate restTemplate;

	public VideoCatAPIGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Retryable(
			include = { ServiceUnavailableException.class }
	)
	public List<Video> list() {
		ResponseEntity<String> response = execute(() ->  restTemplate.exchange("http://localhost:8080/videos", HttpMethod.GET, null, String.class));

		List<Video> list = new ArrayList<>();

		String body = response.getBody();
		DocumentContext context = JsonPath.parse(body);

		int length = context.read("$.length()");
		for (int i = 0; i < length; i++) {
			Long id = Long.valueOf((Integer)context.read("$.[" + i + "].id"));
			String title = context.read("$.[" + i + "].title");
			String rating = context.read("$.[" + i + "].rating");
			list.add(new Video(id, title, rating));
		}

		return list;
	}

	// TODO refactor: extract class
	private <T> T execute(Supplier<T> supplier) {
		try {
			return supplier.get();
		}
		catch (HttpServerErrorException | HttpClientErrorException e) {
			if (503 == e.getRawStatusCode()) {
				throw new ServiceUnavailableException(e);
			}
			else if (404 == e.getRawStatusCode()) {
				throw new NotFoundException(e);
			}

			throw e;
		}
	}

}
