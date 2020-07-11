package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.*;
import com.dtucdio3.digitalshop2.service.CategoryService;
import com.dtucdio3.digitalshop2.service.OrderService;
import com.dtucdio3.digitalshop2.service.ProductService;
import com.dtucdio3.digitalshop2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
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

	@Autowired
	private OrderService orderService;

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
	public String addToCartLink(@PathVariable Integer productId, @ModelAttribute("cart") Cart cart) {
		Product product = productService.get(productId);
		cart.addToCart(product);
		return "redirect:/";
	}

	@PostMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable Integer productId, @ModelAttribute("cart") Cart cart, @RequestParam("quantity") Integer quantity) {
		Product product = productService.get(productId);
		cart.addToCart(product, quantity);
		return "redirect:/";
	}

	@GetMapping("/removeFromCart/{productId}")
	public String removeFromCart(@PathVariable Integer productId, @ModelAttribute("cart") Cart cart) {
		Product product = productService.get(productId);
		cart.removeFromCart(product);
		return "redirect:/";
	}

	@GetMapping("/product-detail/{id}")
	public String productDetail(@PathVariable Integer id, @ModelAttribute("cart") Cart cart, Model model) {
		model.addAttribute("product", productService.get(id));
		return "home/product-detail";
	}

	@GetMapping("/checkout")
	public String checkout(@ModelAttribute("cart") Cart cart) {
		return "home/checkout";
	}

	@PostMapping("/checkout")
	public String processCheckout(@ModelAttribute("cart") Cart cart) {
		if (cart.getTotalQuantity() == 0) {
			return "redirect:/checkout?error";
		} else {
			MyUserPrincipal userPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userPrincipal.getUser();
			Order order = new Order();
			order.setUser(user);
			order.setStatus("Đã đặt hàng.");
			order.setCreateDay(LocalDate.now());
			order.setDeliveryDay(null);
			for (Product p: cart.getProducts()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setQuantity(cart.getProductQuantity(p));
				orderDetail.setId(order, p);
				order.addOrderDetail(orderDetail);
			}
			orderService.save(order);
			cart.clear();
			return "redirect:/checkout?success";
		}
	}


	@GetMapping("/search")
	public String listProduct(Pageable pageable, Model model, HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name = "categoryId", required = false) Integer categoryId ) {
		int page = 0;
		int size = 10;

		if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			if (Integer.parseInt(request.getParameter("page")) < 1) {
				page = 0;
			} else {
				page = Integer.parseInt(request.getParameter("page")) - 1;
			}
		}

		if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			if (Integer.parseInt(request.getParameter("size")) < 1) {
				size = 1;
			} else {
				size = Integer.parseInt(request.getParameter("size"));
			}
		}
		model.addAttribute("products", productService.search(keyword, categoryId, PageRequest.of(page, size)));
		return "home/list-product";
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
