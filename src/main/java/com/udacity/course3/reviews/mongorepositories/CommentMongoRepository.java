package com.udacity.course3.reviews.mongorepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.documents.CommentDocument;

/**
 * Mongo Repository for CommentDocument.
 *
 * @author Nishant
 */
@Repository
public interface CommentMongoRepository extends MongoRepository<CommentDocument, String> {

}
