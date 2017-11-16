package com.example.videocat.api.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Data model for a video.
 */
@Entity
public class Video {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String rating;
	private Integer year;
	private String imbdURL;

	public Video() {}

	public Video(Long id, String title, String rating, Integer year, String imbdURL) {
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.year = year;
		this.imbdURL = imbdURL;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImbdURL() {
		return imbdURL;
	}

	public void setImbdURL(String imbdURL) {
		this.imbdURL = imbdURL;
	}

}
