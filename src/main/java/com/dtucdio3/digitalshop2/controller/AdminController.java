package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.Category;
import com.dtucdio3.digitalshop2.entity.Product;
import com.dtucdio3.digitalshop2.service.CategoryService;
import com.dtucdio3.digitalshop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute(name = "categoryList")
    public List<Category> categoryList() {
        return categoryService.listAll();
    }

    @GetMapping
    public String home() {
        return "dashboard/index";
    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "dashboard/product/create";
    }

    @PostMapping("product/create")
    public String processCreatingProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/admin/product";
    }


    @GetMapping("/product")
    public String listProduct(Pageable pageable, Model model) {
        model.addAttribute("products", productService.findAll(pageable));
        return "dashboard/product/list";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable Integer id) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "dashboard/product/edit";
    }

    @PostMapping("/product/update")
    public String processingEditingProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/delete/{id}")
    public String processDeletingProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/admin/product";
    }
}
