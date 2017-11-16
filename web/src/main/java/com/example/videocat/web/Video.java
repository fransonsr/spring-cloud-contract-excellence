package com.example.videocat.web;

/**
 * Java binding to the web app's interpretation of the video representation.
 */
public class Video {

	private String title;
	private String rating;

	public Video() {}

	public Video(String title, String rating) {
		super();
		this.title = title;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
