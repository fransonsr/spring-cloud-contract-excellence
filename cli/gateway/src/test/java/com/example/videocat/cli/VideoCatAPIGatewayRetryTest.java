package com.example.videocat.cli;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.videocat.cli.exception.NotFoundException;
import com.example.videocat.cli.exception.ServiceUnavailableException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.Scenario;

import util.VerificationMethod;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VideoCatAPIGatewayTestApplication.class)
public class VideoCatAPIGatewayRetryTest {

	@Autowired
	VideoCatAPIGateway gateway;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8080);

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void list_noRetry() throws Exception {
		stubFor(get(urlEqualTo("/videos")).willReturn(aResponse().withStatus(200).withBodyFile("api-videos.json")));

		List<Video> videos = gateway.list();

		assertThat(videos, hasSize(3));

		verify(exactly(1), getRequestedFor(urlEqualTo("/videos")));
	}

	@Test
	public void list_404_noRetry() throws Exception {
		stubFor(get(urlEqualTo("/videos")).willReturn(aResponse().withStatus(404)));

		thrown.expect(NotFoundException.class);
		thrown.expect(new VerificationMethod() {
			@Override
			public void verificationMethod(Throwable thrownException) throws Exception {
				verify(exactly(1), getRequestedFor(urlEqualTo("/videos")));
			}
		});

		gateway.list();
	}

	@Test
	public void list_retry_503_success() throws Exception {
		stubFor(get(urlEqualTo("/videos")).inScenario("Service Unavailable").whenScenarioStateIs(Scenario.STARTED)
				.willReturn(aResponse().withStatus(503)).willSetStateTo("503"));

		stubFor(get(urlEqualTo("/videos")).inScenario("Service Unavailable").whenScenarioStateIs("503")
				.willReturn(aResponse().withStatus(200).withBodyFile("api-videos.json")));

		List<Video> videos = gateway.list();

		assertThat(videos, hasSize(3));

		verify(exactly(2), getRequestedFor(urlEqualTo("/videos")));
	}

	@Test
	public void list_retry_503_exausted() throws Exception {
		stubFor(get(urlEqualTo("/videos")).willReturn(aResponse().withStatus(503)));

		thrown.expect(ServiceUnavailableException.class);
		thrown.expect(new VerificationMethod() {
			@Override
			public void verificationMethod(Throwable thrownException) throws Exception {
				verify(exactly(3), getRequestedFor(urlEqualTo("/videos")));
			}
		});

		gateway.list();
	}

}
