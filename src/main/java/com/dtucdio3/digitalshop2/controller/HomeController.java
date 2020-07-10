package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.Category;
import com.dtucdio3.digitalshop2.service.CategoryService;
import com.dtucdio3.digitalshop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String viewHomePage(Model model, HttpServletRequest request) {
		List<Category> categories = categoryService.listAll();
		model.addAttribute("categories", categories);
		return "home/index";
	}

	@GetMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable String productId) {
		return null;
	}
}
