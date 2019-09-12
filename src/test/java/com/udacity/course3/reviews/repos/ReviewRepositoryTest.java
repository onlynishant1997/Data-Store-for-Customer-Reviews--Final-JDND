package com.udacity.course3.reviews.repos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.jparepositories.CommentJpaRepository;
import com.udacity.course3.reviews.jparepositories.ProductJpaRepository;
import com.udacity.course3.reviews.jparepositories.ReviewJpaRepository;

/**
 * Test Class for Review Data Jpa.
 * 
 * @author Nishant
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private CommentJpaRepository commentRepository;
	@Autowired
	private ReviewJpaRepository reviewRepository;
	@Autowired
	private ProductJpaRepository productRepository;

	private static Product product;
	private static Review review;

	/**
	 * Initilization.
	 */
	@BeforeClass
	public static void init() {
		product = new Product();
		product.setName("Samsung");
		product.setCategory("Mobile");
		product.setDescription("4G Mobile");
		review = new Review();
		review.setReview_text("Good Product");
		review.setTitle("Howz the product");
	}

	/**
	 * Test injected components are not null.
	 */
	@Test
	public void testInjectedComponentsAreNotNull() {
		assertThat(productRepository).isNotNull();
		assertThat(commentRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
	}

	/**
	 * Test save review for product.
	 */
	@Test
	public void testSaveReviewForProduct() {
		Product savedProduct = productRepository.save(product);
		review.setProduct(savedProduct);
		Review expectedReview = reviewRepository.save(review);
		assertThat(expectedReview).isNotNull();
		Optional<Review> optionalReview = reviewRepository.findById(expectedReview.getId());
		Review actualReview = optionalReview.get();
		assertThat(actualReview).isNotNull();
		assertTrue(actualReview.getId().equals(expectedReview.getId()));
	}

	/**
	 * Test find all review by product.
	 */
	@Test
	public void testFindAllReviewByProduct() {
		Product savedProduct = productRepository.save(product);
		review.setProduct(savedProduct);
		Review expectedReview = reviewRepository.save(review);
		assertThat(expectedReview).isNotNull();
		List<Review> actualReviewList = reviewRepository.findAll();
		assertTrue(actualReviewList.size() == 1);
		Review actualReview = actualReviewList.get(0);
		assertThat(expectedReview.getId().equals(actualReview.getId()));
	}

}
