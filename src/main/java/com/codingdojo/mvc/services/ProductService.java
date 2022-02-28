package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Product;
import com.codingdojo.mvc.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	// //// GET ALL PRODUCTS ////////////
	public List<Product> readAllProducts() {
		System.out.println("get all products from db");
		return productRepository.findAll();
	}
	// //// CREATE NEW PRODUCT ////////////
	public Product create(Product product) {
		System.out.println("create product db");
		return productRepository.save(product);
	}
	// //// GET PRODUCT BY ID ///////////
	public Product readById(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			System.out.println("get product by id db");
			return optional.get();
		}
		return null;
	}
	
	// //// UPDATE PRODUCT ///////////
	public Product updateProduct(Long product_id, Product product) {
		Optional<Product> optional = productRepository.findById(product_id);
		if (optional.isPresent()) {
			System.out.println("update product db");
			Product data = optional.get();
			data.setCategories(product.getCategories());
			return productRepository.save(data);
		} else {
			return null;
		}
	}
}
