package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.Category;
import com.dtucdio3.digitalshop2.service.CategoryService;
import com.dtucdio3.digitalshop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model, HttpServletRequest request) {
		List<Category> categories = categoryService.listAll();
		model.addAttribute("categories", categories);
		return "home/index";
	}
}