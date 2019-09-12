package com.udacity.course3.reviews.mongorepositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.documents.ReviewDocument;

/**
 * Mongo Repository for ReviewDocument.
 *
 * @author Nishant
 */
@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {

	/**
	 * Find all by product id.
	 *
	 * @param productId the product id
	 * @return the list
	 */
	List<ReviewDocument> findAllByProductId(String productId);
	
	/**
	 * Find by review id from sql.
	 *
	 * @param reviewIdFromSql the review id from sql
	 * @return the optional
	 */
	Optional<ReviewDocument> findByReviewIdFromSql(String reviewIdFromSql);

}
