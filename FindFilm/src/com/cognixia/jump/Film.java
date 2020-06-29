package com.cognixia.jump;

public class Film {
	private int id;
	private String title;
	private String description;
	private String genre;
	private double price;
	private int length;
	private String rating;
	private String actors;
	
	public Film(int id, String title, String description, String genre, double price, int length, String rating, String actors) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.price = price;
		this.length = length;
		this.rating = rating;
		this.actors = actors;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the actors
	 */
	public String getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(String actors) {
		this.actors = actors;
	}
}
