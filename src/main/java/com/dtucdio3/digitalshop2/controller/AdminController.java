package com.dtucdio3.digitalshop2.controller;

import com.dtucdio3.digitalshop2.entity.*;
import com.dtucdio3.digitalshop2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

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

    PromotionDetailService promotionDetailService;

    @Autowired
    public void setPromotionDetailService(PromotionDetailService promotionDetailService) {
        this.promotionDetailService = promotionDetailService;
    }

    @ModelAttribute(name = "promotionDetails")
    public List<PromotionDetail> promotionDetails() {
        return promotionDetailService.findAll();
    }

    @ModelAttribute(name = "categoryList")
    public List<Category> categoryList() {
        return categoryService.listAll();
    }

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    OrderDetailService orderDetailService;

    @Autowired
    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public String home() {
        return "dashboard/index";
    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new ProductImage());
        return "dashboard/product/create";
    }

//    @PostMapping("product/create")
//    public String processCreatingProduct(@ModelAttribute ProductImage productImage) {
//        return "redirect:/admin/product";
//    }


    @GetMapping("/product")
    public String listProduct(HttpServletRequest request, Model model) {
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
        model.addAttribute("products", productService.findAll(PageRequest.of(page, size)));
        model.addAttribute("baseUrl", getBaseURL(request));
        return "dashboard/product/list";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable Integer id) {
        Product product = productService.get(id);
        ProductImage productImage = new ProductImage();
        productImage.setId(product.getId());
        productImage.setName(product.getName());
        productImage.setPrice(product.getPrice());
        productImage.setQuantity(product.getQuantity());
        productImage.setDescription(product.getDescription());
        productImage.setCategory(product.getCategory());
        model.addAttribute("product", productImage);
        return "dashboard/product/edit";
    }

//    @PostMapping("/product/update")
//    public String processingEditingProduct(@ModelAttribute Product product) {
//        productService.save(product);
//        return "redirect:/admin/product";
//    }

    @GetMapping("/product/delete/{id}")
    public String processDeletingProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/{id}/promotion")
    public String addPromotion(@PathVariable Integer id, Model model) {
        model.addAttribute("productId", id);
        model.addAttribute("promotionDetail", new PromotionDetail());
        Product product = productService.get(id);
        model.addAttribute("productPromotionDetail", product.getPromotionDetails());
        return "dashboard/product/add-promotion";
    }

    @PostMapping("/product/{id}/promotion")
    public String processAddingPromotion(@PathVariable Integer id, @ModelAttribute PromotionDetail promotionDetail) {
        Product product = productService.get(id);
        product.addPromotionDetail(promotionDetail);
        productService.save(product);
        return "redirect:/admin/product/{1}/promotion".replace("{1}", id.toString());
    }

    @GetMapping("/product/{productId}/remove/promotion/{promotionId}")
    public String removePromotion(@PathVariable Integer productId, @PathVariable Integer promotionId) {
        Product product = productService.get(productId);
        for (PromotionDetail p : product.getPromotionDetails()) {
            if (p.getId() == promotionId) {
                product.getPromotionDetails().remove(p);
                break;
            }
        }
        productService.save(product);
        return "redirect:/admin/product/{1}/promotion".replace("{1}", productId.toString());
    }


    @GetMapping("/promotion")
    public String listPromotion() {
        return "dashboard/promotion/list";
    }

    @GetMapping("/promotion/create")
    public String createPromotion(Model model) {
        model.addAttribute("promotion", new PromotionDetail());
        return "dashboard/promotion/create";
    }

    @PostMapping("/promotion/create")
    public String createPromotion(@ModelAttribute PromotionDetail promotionDetail) {
        promotionDetailService.save(promotionDetail);
        return "redirect:/admin/promotion";
    }

    @GetMapping("/promotion/edit/{id}")
    public String editPromotion(Model model, @PathVariable Integer id) {
        PromotionDetail promotionDetail = promotionDetailService.findById(id);
        model.addAttribute("promotion", promotionDetail);
        return "dashboard/promotion/edit";
    }


    @PostMapping("/promotion/update")
    public String updatePromotion(@ModelAttribute PromotionDetail promotionDetail) {
        promotionDetailService.save(promotionDetail);
        return "redirect:/admin/promotion";
    }

    @GetMapping("/promotion/delete/{id}")
    public String editPromotion(@PathVariable Integer id) {
        promotionDetailService.deleteById(id);
        return "redirect:/admin/promotion";
    }

    @GetMapping("/user")
    public String listUser(Model model, HttpServletRequest request) {
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
        model.addAttribute("users", userService.findAll(PageRequest.of(page, size)));
        return "dashboard/user/list";
    }

    @GetMapping("/user/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "dashboard/user/create";
    }

    @PostMapping("/user/create")
    public String processCreatingUser(@ModelAttribute User user) {
        userService.registerNewUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/edit/{id}")
    public String updateUser(Model model, @PathVariable Long id) {
        User user = userService.findUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "dashboard/user/edit";
    }

    @PostMapping("/user/update")
    public String processUpdatingUser(@ModelAttribute User user) {
        userService.registerNewUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/delete/{id}")
    public String updateUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/user";
    }


    @GetMapping("/user/{userId}/role")
    public String addRole(@PathVariable Long userId, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("role", new Role());
        User user = userService.findUserById(userId).orElse(null);
        model.addAttribute("userRoles", user.getRoles());
        return "dashboard/user/add-role";
    }

    @PostMapping("/user/{userId}/role")
    public String processAddingRole(@PathVariable Long userId, @ModelAttribute Role role) {
        User user = userService.findUserById(userId).orElse(null);
        user.addRole(role);
        userService.updateUser(user);
        return "redirect:/admin/user/" + userId + "/role";
    }


    @GetMapping("/user/{userId}/remove/role/{roleId}")
    public String processRemovingRole(@PathVariable Long userId, @PathVariable Long roleId) {
        User user = userService.findUserById(userId).orElse(null);
        for (Role role : user.getRoles()) {
            if (role.getId() == roleId) {
                user.getRoles().remove(role);
                break;
            }
        }
        userService.updateUser(user);
        return "redirect:/admin/user/" + userId + "/role";
    }

    @GetMapping("/order")
    public String listOrder(Model model, HttpServletRequest request) {
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
        model.addAttribute("orders", orderService.findAll(PageRequest.of(page, size)));
        return "dashboard/order/list";
    }

    @GetMapping("/order/create")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        return "dashboard/order/create";
    }

    @PostMapping("/order/create")
    public String processCreatingOrder(@ModelAttribute Order order) {
        order.setCreateDay(LocalDate.now());
        orderService.save(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/order/edit/{id}")
    public String updateOrder(Model model, @PathVariable Integer id) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "dashboard/order/edit";
    }

    @PostMapping("/order/update")
    public String processUpdatingOrder(@ModelAttribute Order order) {
        order.setCreateDay(orderService.findById(order.getId()).getCreateDay());
        orderService.save(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/order/delete/{id}")
    public String updateOrder(@PathVariable Integer id) {
        orderService.deleteById(id);
        return "redirect:/admin/order";
    }

    @GetMapping("/order/{orderId}/orderDetail")
    public String addOrderDetail(@PathVariable Integer orderId, Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.listAll());
        Order order = orderService.findById(orderId);
        model.addAttribute("orderDetails", order.getDetail());
        return "dashboard/order/add-order-detail";
    }

    @PostMapping("/order/{orderId}/orderDetail")
    public String processAddingOrderDetail(@PathVariable Integer orderId, @ModelAttribute Product product, @RequestParam Integer quantity) {
        Order order = orderService.findById(orderId);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(order, product);
        OrderDetail checkExistOrderDetail = orderDetailService.findById(orderDetail.getId());
        if (checkExistOrderDetail != null) {
            orderDetail.setQuantity(checkExistOrderDetail.getQuantity() + quantity);
        } else {
            orderDetail.setQuantity(quantity);
        }
        orderDetailService.save(orderDetail);
        return "redirect:/admin/order/" + orderId + "/orderDetail";
    }


    @GetMapping("/order/{orderId}/remove/orderDetail/{productId}")
    public String processRemovingOrderDetail(@PathVariable Integer orderId, @PathVariable Integer productId) {
        Order order = orderService.findById(orderId);
        for (OrderDetail orderDetail: order.getDetail()) {
            if (orderDetail.getId().getProduct().getId() == productId) {
                orderDetailService.deleteById(orderDetail.getId());
                order.getDetail().remove(orderDetail);
                break;
            }
        }
        orderService.save(order);
        return "redirect:/admin/order/" + orderId + "/orderDetail";
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public String processCreatingProduct(HttpServletRequest request, //
                                           Model model, //
                                           @ModelAttribute("productImage") ProductImage productImage) {

        return this.doUpload(request, model, productImage);
    }
    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String processUpdatingProduct(HttpServletRequest request, //
                                           Model model, //
                                           @ModelAttribute("productImage") ProductImage productImage) {

        return this.doUpload(request, model, productImage);
    }

    private String doUpload(HttpServletRequest request, Model model, //
                            ProductImage productImage) {
        Product product = new Product();
        product.setId(productImage.getId());
        product.setName(productImage.getName());
        product.setCategory(productImage.getCategory());
        product.setDescription(productImage.getDescription());
        product.setPrice(productImage.getPrice());
        product.setQuantity(product.getQuantity());
        productService.save(product);
        // Thư mục gốc upload file.
        String uploadRootPath = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = productImage.getFileData();
        //
        List<File> uploadedFiles = new ArrayList<File>();
        List<String> failedFiles = new ArrayList<String>();

        for (MultipartFile fileData : fileDatas) {

            // Tên file gốc tại Client.
            String name = fileData.getOriginalFilename();

            if (name != null && name.length() > 0) {
                try {
                    // Tạo file tại Server.
                    String newName = System.currentTimeMillis() + name;
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + newName);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                    failedFiles.add(name);
                }
            }
        }
        for (File file : uploadedFiles) {
            Image image = new Image();
            image.setImageUrl(file.getName());
            image.setProduct(product);
            product.addImage(image);
        }
        productService.save(product);
        return "redirect:/admin/product";
    }
    //get base URL
    public String getBaseURL(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
