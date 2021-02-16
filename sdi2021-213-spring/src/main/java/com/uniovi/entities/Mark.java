package com.uniovi.entities;

public class Mark {

	private Long id;
	private String email;
	private Double score;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	public Mark(Long id, String email, Double score) {
		super();
		this.id = id;
		this.email = email;
		this.score = score;
	}

	public Mark() {
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", email=" + email + ", score=" + score + "]";
	}

}
