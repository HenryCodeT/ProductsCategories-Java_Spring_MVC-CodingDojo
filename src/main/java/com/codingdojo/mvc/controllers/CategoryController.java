package com.codingdojo.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.mvc.models.Category;
import com.codingdojo.mvc.models.Product;
import com.codingdojo.mvc.services.CategoryService;
import com.codingdojo.mvc.services.ProductService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	// //// NEW CATEGORY /////

	// //// CREATE NEW PRODUCT /////
	@PostMapping("category/new")
	public String addProduct(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
		System.out.println("*************** POST NEW CATEGORY **************");
		if (result.hasErrors()) {
			model.addAttribute("product", new Product());
			model.addAttribute("category", new Category());
			model.addAttribute("categories", categoryService.readAllCategories());
			model.addAttribute("products", productService.readAllProducts());
			return "index";
		} else {
			categoryService.create(category);
			return "redirect:/";
		}
	}

	// //// GET CATEGORY ////////
	@GetMapping("category/{id}")
	public String showCat(@PathVariable("id") Long id, Model model) {
		System.out.println("************** CATEGORY VIEW *************");
		Category thisCategory = categoryService.readById(id);

		List<Product> allProducts = productService.readAllProducts();

		List<Product> categoryProductslList = thisCategory.getProducts();

		for (int i = 0; i < categoryProductslList.size(); i++) {
			if (allProducts.contains(categoryProductslList.get(i))) {
				allProducts.remove(categoryProductslList.get(i));
			}
		}
		
		model.addAttribute("category", thisCategory);
		model.addAttribute("categorysProducts", categoryProductslList);
		model.addAttribute("availableProducts", allProducts);
		System.out.println("--------------- CATEGORY VIEW ------------------");
		return "showcategory";
	}
	// //// POST CATEGORY //////////
		@GetMapping("/addProduct/{id}")
		public String addCategoryToProduct(@PathVariable("id") Long category_id, @RequestParam("product_id") Long product_id) {
			
			Product product = productService.readById(product_id);
			Category category = categoryService.readById(category_id);
			
			List<Product> categoryProducts = category.getProducts();
			categoryProducts.add(product);
			// --------- update product -------------
			category.setProducts(categoryProducts);
			categoryService.updateCategory(category_id,category);
			return "redirect:/category/"+category_id;
		}
}
