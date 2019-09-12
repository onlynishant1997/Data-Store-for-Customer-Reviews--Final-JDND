package com.udacity.course3.reviews.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.documents.ReviewDocument;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.jparepositories.ReviewJpaRepository;
import com.udacity.course3.reviews.mongorepositories.ReviewMongoRepository;

/**
 * Service Class for Review Repository.
 *
 * @author Nishant
 */
@Service
public class ReviewService {
	@Autowired
	private ReviewMongoRepository reviewMongoRepository;

	@Autowired
	private ReviewJpaRepository reviewJpaRepository;

	private Logger log = Logger.getLogger(ReviewService.class);

	/**
	 * Save review for product.
	 *
	 * @param product        the product
	 * @param reviewDocument the review
	 * @return the review
	 */
	public ReviewDocument saveReviewForProduct(Product product, Review review) {
		review.setProduct(product);
		Review savedReview = reviewJpaRepository.save(review);
		log.info("Saved Review in SQL : " + savedReview);
		ReviewDocument reviewDocument = new ReviewDocument();
		reviewDocument.setReview_text(savedReview.getReview_text());
		reviewDocument.setTitle(savedReview.getTitle());
		reviewDocument.setProductId(String.valueOf(product.getId()));
		reviewDocument.setReviewIdFromSql(String.valueOf(savedReview.getId()));
		ReviewDocument savedReviewDocument = reviewMongoRepository.save(reviewDocument);
		log.info("Saved Review in MongoDb : " + reviewDocument);
		return savedReviewDocument;
	}

	
	/**
	 * List reviews for product from mongo db.
	 *
	 * @param productId the product id
	 * @return the list
	 */
	public List<ReviewDocument> listReviewsForProductFromMongoDb(Integer productId) {
		return reviewMongoRepository.findAllByProductId(String.valueOf(productId));
	}

	/**
	 * Find review by id in mongo.
	 *
	 * @param reviewId the review id
	 * @return the optional
	 */
	public Optional<ReviewDocument> findReviewByIdFromMongo(int reviewId) {
		return reviewMongoRepository.findByReviewIdFromSql(String.valueOf(reviewId));
	}
	

	/**
	 * Find review by id from SQL.
	 *
	 * @param reviewId the review id
	 * @return the optional
	 */
	public Optional<Review> findReviewByIdFromSQL(int reviewId) {
		return reviewJpaRepository.findById(reviewId);
	}

}
