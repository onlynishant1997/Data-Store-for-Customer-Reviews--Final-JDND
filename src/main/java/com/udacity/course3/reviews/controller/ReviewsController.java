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

import com.udacity.course3.reviews.documents.ReviewDocument;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.services.ProductService;
import com.udacity.course3.reviews.services.ReviewService;

/**
 * Controller for review entity.
 *
 * @author Nishant
 */
@RestController
public class ReviewsController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewService reviewService;
	
	

	/**
	 * Creates the review for product.
	 *
	 * @param productId the product id
	 * @param review    the review
	 * @return the Review or 404 if review not found.
	 */
	@RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
	public ResponseEntity<ReviewDocument> createReviewForProduct(@PathVariable("productId") Integer productId,
			@Valid @RequestBody Review review) {
		Optional<Product> optional = productService.findById(productId);
		if (optional.isPresent()) {
			Product product = optional.get();
			return ResponseEntity.ok(reviewService.saveReviewForProduct(product, review));
		}
		return new ResponseEntity<ReviewDocument>(HttpStatus.NOT_FOUND);
	}

	/**
	 * List reviews for product.
	 *
	 * @param productId the product id
	 * @return the list of reviews.
	 */
	@RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewDocument>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
		List<ReviewDocument> reviews = reviewService.listReviewsForProductFromMongoDb(productId);
		if (reviews.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reviews);
	}
}