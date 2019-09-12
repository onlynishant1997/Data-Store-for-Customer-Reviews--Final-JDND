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
 * Review Entity
 *
 * @author Nishant
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Please Provide Title")
	private String title;
	@NotNull(message = "Please Provide Review Text")
	@Column(name = "review_text")
	private String review_text;
	@ManyToOne
	private Product product;

	public Review() {
	}

	public Review(Integer id, @NotNull(message = "Please Provide Title") String title,
			@NotNull(message = "Please Provide Review Text") String review_text, Product product) {
		super();
		this.id = id;
		this.title = title;
		this.review_text = review_text;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
