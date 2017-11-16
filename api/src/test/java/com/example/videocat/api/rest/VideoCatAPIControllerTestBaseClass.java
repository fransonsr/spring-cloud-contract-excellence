package com.example.videocat.api.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.videocat.api.VideoCatAPIApplication;
import com.example.videocat.api.rest.VideoCatAPIController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


@SpringBootTest(classes = {VideoCatAPIApplication.class})
@RunWith(SpringRunner.class)
public class VideoCatAPIControllerTestBaseClass {

	@Autowired
	private VideoCatAPIController videoCatAPIController;

	@Before
	public void before() {
		// it is using RestAssured 2.x for some reason...
		RestAssuredMockMvc.standaloneSetup(this.videoCatAPIController);
	}

}
