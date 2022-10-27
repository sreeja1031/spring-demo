package com.springdemo.project.controller;

import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.CartService;
import com.springdemo.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public  String test(Model model, HttpServletRequest httpServletRequest)
    {
            List<ProductDTO> productList =   productService.getProducts();
            model.addAttribute("products", productList);
            if(httpServletRequest.isUserInRole("ROLE_admin"))
            {
                return "redirect:/admin/products";
            }
            else {
                return "home";
            }
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
