package com.codingdojo.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingdojo.mvc.models.Category;
import com.codingdojo.mvc.models.Product;
import com.codingdojo.mvc.services.CategoryService;
import com.codingdojo.mvc.services.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public String redirect() {
		return "redirect:/main";
	}
	
	@GetMapping("/main")
	public String main(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.readAllCategories());
		model.addAttribute("products", productService.readAllProducts());
		return "index";
	}
}
