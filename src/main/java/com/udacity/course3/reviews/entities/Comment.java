package com.udacity.course3.reviews.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Comment Entity
 * 
 * @author Nishant
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Please Provide Heading")
	private String heading;
	@NotNull(message = "Please Provide Comment")
	@Column(name = "comment_text")
	private String comment;
	@ManyToOne
	private Review review;

	public Comment() {
	}

	public Comment(Integer id, String heading, String comment, Review review) {
		super();
		this.id = id;
		this.heading = heading;
		this.comment = comment;
		this.review = review;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", heading=" + heading + ", comment=" + comment + ", review=" + review + "]";
	}

}
