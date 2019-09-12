package com.udacity.course3.reviews.mongorepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.course3.reviews.documents.CommentDocument;
import com.udacity.course3.reviews.documents.ReviewDocument;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.mongorepositories.CommentMongoRepository;
import com.udacity.course3.reviews.mongorepositories.ReviewMongoRepository;

/**
 * Test Class for ReviewMongo.
 *
 * @author Nishant
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewMongoTest {

	@Autowired
	CommentMongoRepository commentRepository;
	@Autowired
	ReviewMongoRepository reviewRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	private Product product;
	private Review review1;
	private Review review2;
	private ReviewDocument review3;
	private ReviewDocument review4;
	private CommentDocument comment1;
	private CommentDocument comment2;

	/**
	 * Initialization.
	 */
	@Before
	public void init() {
		product = new Product();
		product.setId(1);
		product.setName("BMW");
		product.setCategory("Deluxe Cars");
		product.setDescription("Costly Car");
		review1 = new Review();
		review1.setId(1);
		review1.setReview_text("Review Text");
		review1.setTitle("Review Title");
		review2 = new Review();
		review2.setId(2);
		review2.setReview_text("Review Text");
		review2.setTitle("Review Title");
		review3 = new ReviewDocument();
		review3.setReview_text("Good Review");
		review3.setTitle("Review Title");
		review3.setReviewIdFromSql(String.valueOf(review1.getId()));
		review3.setProductId(String.valueOf(product.getId()));
		review4 = new ReviewDocument();
		review4.setReview_text("Bad Review");
		review4.setTitle("Bad Review title");
		review4.setReviewIdFromSql(String.valueOf(review1.getId()));
		review4.setProductId(String.valueOf(product.getId()));
		comment1 = new CommentDocument();
		comment2 = new CommentDocument();
		comment1.setHeading("Comment");
		comment1.setComment("Good");
		comment2.setComment("second comment");
		comment2.setHeading("second comment heading");
	}

	/**
	 * Test embedded mongo db.
	 */
	@Test
	public void testEmbeddedMongoDb() {
		DBObject objectToSave = BasicDBObjectBuilder.start().add("key", "value").get();

		mongoTemplate.save(objectToSave, "collection");

		assertThat(mongoTemplate.findAll(DBObject.class, "collection")).isNotEmpty();
	}

	/**
	 * Test injected components are not null.
	 */
	@Test
	public void testInjectedComponentsAreNotNull() {
		assertThat(reviewRepository).isNotNull();
		assertThat(commentRepository).isNotNull();
	}

	/**
	 * Test find all reviews by product id.
	 */
	@Test
	public void testFindAllReviewsByProductId() {
		ReviewDocument savedReview1 = reviewRepository.save(review3);
		assertTrue(savedReview1 != null);
		ReviewDocument savedReview2 = reviewRepository.save(review4);
		assertTrue(savedReview2 != null);
		assertTrue(reviewRepository.findAllByProductId(String.valueOf(product.getId())).size() == 2);
	}

	/**
	 * Test find all reviews by review id.
	 */
	@Test
	public void testFindAllReviewsByReviewId() {
		ReviewDocument savedReview1 = reviewRepository.save(review3);
		assertTrue(savedReview1 != null);
		ReviewDocument rd =reviewRepository.findByReviewIdFromSql(savedReview1.getReviewIdFromSql()).get();
		assertTrue(rd.getReviewIdFromSql().equals(savedReview1.getReviewIdFromSql()));
	}

	/**
	 * Test save comment for review.
	 */
	@Test
	public void testSaveCommentForReview() {
		List<CommentDocument> comment = new ArrayList<CommentDocument>();
		comment.add(comment1);
		review3.setComments(comment);
		ReviewDocument review = reviewRepository.save(review3);
		List<CommentDocument> listComment = review.getComments();
		assertTrue(listComment.size() == 1);
		listComment.add(comment2);
		review3.setComments(listComment);
		ReviewDocument updatedReview = reviewRepository.save(review3);
		assertTrue(updatedReview.getComments().size() == 2);

	}
}
