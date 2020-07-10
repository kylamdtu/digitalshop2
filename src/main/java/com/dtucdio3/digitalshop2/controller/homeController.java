package com.dtucdio3.digitalshop2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dtucdio3.digitalshop2.entity.Category;
import com.dtucdio3.digitalshop2.entity.Product;
import com.dtucdio3.digitalshop2.service.CategoryService;
import com.dtucdio3.digitalshop2.service.ProductService;

@Controller
public class homeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		for (Category category : categoryService.listAll()) {
			List<Product> products = productService.listByCategory(category.getName());
			model.addAttribute("list"+category.getId(), products);	
		}
		List<Category> categories = categoryService.listAll();
		model.addAttribute("categories", categories);
		return "home/index";
	}
	
	
}
