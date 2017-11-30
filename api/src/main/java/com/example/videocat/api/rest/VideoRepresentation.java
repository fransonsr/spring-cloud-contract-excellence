package com.example.videocat.api.rest;

/**
 * REST representation for a video. This represents the "schema" for the API.
 */
public class VideoRepresentation {

	// TODO: remove the 'id' from the representation (and clients) because we
	// should not
	// expose the database synthetic identifier to the public. This will create
	// a case
	// where we can demonstrate how the Spring Cloud Contract verifier and
	// generated
	// stub will help identify breaking changes with clients.

	private Long id;
	private String title;
	private String rating;

	public VideoRepresentation() {
	}

	public VideoRepresentation(Long id, String title, String rating) {
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
