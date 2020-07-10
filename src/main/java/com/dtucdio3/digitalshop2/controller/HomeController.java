package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.*;
import com.dtucdio3.digitalshop2.service.CategoryService;
import com.dtucdio3.digitalshop2.service.ProductService;
import com.dtucdio3.digitalshop2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class HomeController {
	UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("cart")
	public Cart cart() {
		return new Cart();
	}

	@ModelAttribute("categories")
	public List<Category> categories() {
		return categoryService.listAll();
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model, @ModelAttribute("cart") Cart cart) {
		System.out.println(cart.getProducts().size());
		List<Category> categories = categoryService.listAll();
		return "home/index";
	}

	@GetMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable Integer productId, @ModelAttribute("cart") Cart cart) {
		Product product = productService.get(productId);
		cart.addToCart(product);
		return "redirect:/";
	}

	@GetMapping("/removeFromCart/{productId}")
	public String removeFromCart(@PathVariable Integer productId, @ModelAttribute("cart") Cart cart) {
		Product product = productService.get(productId);
		cart.removeFromCart(product);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new Register());
		return "home/login-register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute("user") @Valid Register user, BindingResult result) {
		if (result.hasErrors()) {
			return "home/login-register";
		}
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setName(user.getName());
		userService.registerNewUser(newUser);
		return "redirect:/login" + "?register";
	}
}
