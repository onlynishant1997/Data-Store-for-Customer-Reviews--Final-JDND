package com.udacity.course3.reviews.documents;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MongoDb Document for Review
 * 
 * @author Nishant
 */
@Document(collection = "reviews")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDocument {

	@Id
	private String id;

	private String reviewIdFromSql;
	@NotNull(message = "Please Provide Title")
	private String title;
	@NotNull(message = "Please Provide Review Text")
	private String review_text;

	@JsonIgnore
	private String productId;

	@JsonIgnore
	List<CommentDocument> comments = new ArrayList<CommentDocument>();

	public ReviewDocument() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReviewIdFromSql() {
		return reviewIdFromSql;
	}

	public void setReviewIdFromSql(String reviewIdFromSql) {
		this.reviewIdFromSql = reviewIdFromSql;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<CommentDocument> getComments() {
		return comments;
	}

	public void setComments(List<CommentDocument> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ReviewDocument [id=" + id + ", reviewIdFromSql=" + reviewIdFromSql + ", title=" + title
				+ ", review_text=" + review_text + ", productId=" + productId + ", comments=" + comments + "]";
	}

}
