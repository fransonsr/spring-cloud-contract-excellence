package com.example.videocat.cli;

/**
 * Local Java binding of the video representation published by the API.
 */
public class Video {

	private Long id;
	private String title;
	private String rating;

	public Video() {}

	public Video(Long id, String title, String rating) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
