package com.udacity.course3.reviews.documents;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MongoDb Document of Comment.
 *
 * @author Nishant
 */
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDocument {

	@Id
	private String id;

	private String commentIdFromSql;

	@NotNull(message = "Please Provide Heading")
	private String heading;
	@NotNull(message = "Please Provide Comment")
	private String comment;

	public CommentDocument() {
	}

	public CommentDocument(String id, String commentIdFromSql,
			@NotNull(message = "Please Provide Heading") String heading,
			@NotNull(message = "Please Provide Comment") String comment) {
		super();
		this.id = id;
		this.commentIdFromSql = commentIdFromSql;
		this.heading = heading;
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentIdFromSql() {
		return commentIdFromSql;
	}

	public void setCommentIdFromSql(String commentIdFromSql) {
		this.commentIdFromSql = commentIdFromSql;
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

	@Override
	public String toString() {
		return "CommentDocument [id=" + id + ", commentIdFromSql=" + commentIdFromSql + ", heading=" + heading
				+ ", comment=" + comment + "]";
	}

}
