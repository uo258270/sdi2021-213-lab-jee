package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Mark {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Double score;
	private Boolean resend = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Mark(Long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.score = score;
	}

	public Mark(String description, Double score, User user) {
		super();
		this.description = description;
		this.score = score;
		this.user = user;
	}

	public Boolean getResend() {
		return resend;
	}

	public void setResend(Boolean resend) {
		this.resend = resend;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

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
	public String getDescription() {
		return description;
	}

	/**
	 * @param email the email to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	public Mark() {
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + score + "]";
	}

}
