package com.udacity.course3.reviews.jparepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;

/**
 * Data Jpa Repository for Review Entity.
 *
 * @author Nishant
 */
@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Integer> {
	
	/**
	 * Find all Review by product.
	 *
	 * @param product the product
	 * @return the list of Reviews
	 */
	List<Review> findAllByProduct(Product product);
}
