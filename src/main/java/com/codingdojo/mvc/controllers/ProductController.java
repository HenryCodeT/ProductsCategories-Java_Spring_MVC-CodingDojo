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
public class ProductController {
	
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	// //// CREATE NEW PRODUCT /////
	@PostMapping("products/new")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		System.out.println("*************** POST NEW PRODUCT **************");
		if(result.hasErrors()) {
			model.addAttribute("product", new Product());
			model.addAttribute("category", new Category());
			model.addAttribute("categories", categoryService.readAllCategories());
			model.addAttribute("products", productService.readAllProducts());
			return "index";
		}
		else {
			productService.create(product);
			return "redirect:/";
		}
	}
	// //// GET PRODUCT ////////
	@GetMapping("products/{id}")
	public String showProd(@PathVariable("id") Long id, Model model) {
		
		Product thisProduct = productService.readById(id);
		
		List<Category> allCategories = categoryService.readAllCategories();
		
		List<Category> productCategoriesList = thisProduct.getCategories();
		
		for(int i=0; i < productCategoriesList.size(); i++) {
			if(allCategories.contains(productCategoriesList.get(i))) {
				allCategories.remove(productCategoriesList.get(i));
			}
		}
		
		model.addAttribute("product", thisProduct);
		model.addAttribute("availableCategories", allCategories);
		model.addAttribute("productsCategories", productCategoriesList);
		return "showProduct";
	}
	// //// POST PRODUCT //////////
	@GetMapping("/addCategory/{id}")
	public String addCategoryToProduct(@PathVariable("id") Long product_id, @RequestParam("newCategory") Long cat_id) {
		
		Product product = productService.readById(product_id);
		Category category = categoryService.readById(cat_id);
		
		List<Category> productCategories = product.getCategories();
		productCategories.add(category);
		// --------- update product -------------
		product.setCategories(productCategories);
		productService.updateProduct(product_id,product);
		return "redirect:/products/"+product_id;
	}
}
