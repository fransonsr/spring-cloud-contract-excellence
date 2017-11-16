package com.example.videocat.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VideoCatController {

	private final VideoCatAPIGateway gateway;

	public VideoCatController(VideoCatAPIGateway gateway) {
		this.gateway = gateway;
	}

	@RequestMapping("/")
	public String list(Model model) {
		List<Video> videos = gateway.list();

		model.addAttribute("videos", videos);

		return "videoList";
	}
}
