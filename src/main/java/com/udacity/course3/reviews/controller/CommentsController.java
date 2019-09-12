package com.udacity.course3.reviews.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.documents.CommentDocument;
import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.services.CommentService;
import com.udacity.course3.reviews.services.ReviewService;

/**
 * Controller for Comment Entity.
 *
 * @author Nishant
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private ReviewService reviewService;

	
	/**
	 * Creates the comment for review.
	 *
	 * @param reviewId the review id
	 * @param comment the comment
	 * @return the response entity
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
	public ResponseEntity<CommentDocument> createCommentForReview(@PathVariable("reviewId") int reviewId,
			@Valid @RequestBody Comment comment) {
		Optional<Review> optional = reviewService.findReviewByIdFromSQL(reviewId);
		if (optional.isPresent()) {
			return ResponseEntity.ok(commentService.saveCommentForReview(optional.get(), comment));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	/**
	 * List comments for review.
	 *
	 * @param reviewId the review id
	 * @return the response entity
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDocument>> listCommentsForReview(@PathVariable("reviewId") int reviewId) {
		List<CommentDocument> comments = commentService.listCommentsForReviewFromMongo(reviewId);
		if (comments.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(comments);
	}
}