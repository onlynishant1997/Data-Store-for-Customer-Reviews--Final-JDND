package com.udacity.course3.reviews.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.entities.Product;

/**
 * Data Jpa Repository for Product Entity.
 *
 * @author Nishant
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer>{

}
