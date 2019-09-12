package com.udacity.course3.reviews.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.documents.CommentDocument;
import com.udacity.course3.reviews.documents.ReviewDocument;
import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.jparepositories.CommentJpaRepository;
import com.udacity.course3.reviews.mongorepositories.CommentMongoRepository;
import com.udacity.course3.reviews.mongorepositories.ReviewMongoRepository;

/**
 * Service Class for Comment Repository.
 *
 * @author Nishant
 */
@Service
public class CommentService {

	@Autowired
	private ReviewMongoRepository reviewRepository;

	@Autowired
	private CommentMongoRepository commentRepository;

	@Autowired
	private CommentJpaRepository commentJpaRepository;

	private Logger log = Logger.getLogger(CommentService.class);

	/**
	 * Save comment for review.
	 *
	 * @param review  the review
	 * @param comment the comment
	 * @return the comment
	 */
	public CommentDocument saveCommentForReview(Review review, Comment comment) {
		comment.setReview(review);
		Comment savedComment = commentJpaRepository.save(comment);
		log.info("Comment Saved in SQL : " + savedComment);
		CommentDocument commentDocument = new CommentDocument();
		commentDocument.setCommentIdFromSql(String.valueOf(savedComment.getId()));
		commentDocument.setComment(savedComment.getComment());
		commentDocument.setHeading(savedComment.getHeading());
		CommentDocument savedCommentDocument = commentRepository.save(commentDocument);
		log.info("Comment Saved in Mongo : " + savedCommentDocument);
		ReviewDocument reviewDocument = reviewRepository.findByReviewIdFromSql(String.valueOf(review.getId())).get();
		List<CommentDocument> listOfComment = reviewDocument.getComments();
		listOfComment.add(savedCommentDocument);
		reviewDocument.setComments(listOfComment);
		ReviewDocument updatedReviewDocument = reviewRepository.save(reviewDocument);
		log.info("Added Comments In Review : " + updatedReviewDocument);
		return commentDocument;
	}

	/**
	 * List comments for review.
	 *
	 * @param reviewId the review id
	 * @return the list
	 */
	public List<CommentDocument> listCommentsForReviewFromMongo(int reviewId) {
		Optional<ReviewDocument> optional = reviewRepository.findByReviewIdFromSql(String.valueOf(reviewId));
		List<CommentDocument> list = new ArrayList<CommentDocument>();
		if (optional.isPresent()) {
			ReviewDocument review = optional.get();
			list = review.getComments();
		}
		return list;
	}

	/**
	 * List comments for review from SQL.
	 *
	 * @param reviewId the review id
	 * @return the list
	 */
	public List<Comment> listCommentsForReviewFromSQL(int reviewId) {
		Review review = new Review();
		review.setId(reviewId);
		return commentJpaRepository.findAllByReview(review);
	}

}
