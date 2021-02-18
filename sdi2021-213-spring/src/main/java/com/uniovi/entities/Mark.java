package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Mark {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
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

	public Mark(Long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.score = score;
	}

	public Mark() {
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + score + "]";
	}

}
