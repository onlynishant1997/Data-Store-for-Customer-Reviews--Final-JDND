package com.udacity.course3.reviews.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.jparepositories.ProductJpaRepository;

/**
 * Service Class for Product Repository.
 *
 * @author Nishant
 */
@Service
public class ProductService {
	@Autowired
	private ProductJpaRepository productRepository;

	/**
	 * Save product.
	 *
	 * @param product the product
	 */
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Find product by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	/**
	 * List of products.
	 *
	 * @return the list
	 */
	public List<Product> listOfProducts() {
		return productRepository.findAll();
	}
}
