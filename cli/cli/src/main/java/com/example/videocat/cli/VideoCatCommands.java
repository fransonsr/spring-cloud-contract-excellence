package com.example.videocat.cli;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class VideoCatCommands {

	private final VideoCatAPIGateway gateway;

	public VideoCatCommands(VideoCatAPIGateway gateway) {
		this.gateway = gateway;
	}

	@ShellMethod("list video catalog")
	public void list() {
		List<Video> list = gateway.list();

		for (int i = 0; i < list.size(); i++) {
			Video video = list.get(i);
			System.out
					.println(" " + video.getId() + " - Title: " + video.getTitle() + "; rating: " + video.getRating());
		}
	}

}
