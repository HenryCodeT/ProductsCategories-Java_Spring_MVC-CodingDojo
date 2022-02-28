package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Category;
import com.codingdojo.mvc.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	// //// GET ALL CATEGORIES ////
	public List<Category> readAllCategories(){
		System.out.println("get all categories from db");
		return  categoryRepository.findAll();
	}
	// //// CREATE NEW CATEGORI //////
	public Category create(@Valid Category category) {
		System.out.println("---------create new category db-----------");
		return categoryRepository.save(category);
	}
	// //// GET CATEGORY BY ID ////////
	public Category readById(Long id) {
		Optional<Category> optional = categoryRepository.findById(id);
		if (optional.isPresent()) {
			System.out.println("---------get category by id db-----------");
			return optional.get();
		} else {
			return null;
		}
	}
	// //// UPDATE CATEGORY /////////
	public Category updateCategory(Long category_id, Category category) {
		Optional<Category> optional = categoryRepository.findById(category_id);
		if (optional.isPresent()) {
			System.out.println("update category from db");
			Category data = optional.get();
			data.setProducts(category.getProducts());
			return categoryRepository.save(data);
		} else {
			return null;
		}
	}
}
