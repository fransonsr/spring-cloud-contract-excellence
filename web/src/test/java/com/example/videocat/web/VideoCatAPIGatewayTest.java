package com.example.videocat.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VideoCatWebApplication.class)
@AutoConfigureStubRunner(workOffline = true, ids = {"com.example.videocat:api:+:8080"})
public class VideoCatAPIGatewayTest {

	@Autowired
	private VideoCatAPIGateway gateway;

	@Test
	public void list() throws Exception {
		List<Video> actual = gateway.list();
		assertThat(actual, hasSize(3));
		Video first = actual.get(0);
		assertThat(first.getTitle(), is("Dumbo"));
		assertThat(first.getRating(), is("G"));
	}

}
