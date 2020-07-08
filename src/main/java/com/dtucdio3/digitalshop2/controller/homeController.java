package com.dtucdio3.digitalshop2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
	@RequestMapping("/")
	public String home() {
		return "home/index";
	}
}
