package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.Register;
import com.dtucdio3.digitalshop2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dtucdio3.digitalshop2.entity.User;

import javax.validation.Valid;

@Controller
public class AuthController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
        return "redirect:/login";
    }
}
