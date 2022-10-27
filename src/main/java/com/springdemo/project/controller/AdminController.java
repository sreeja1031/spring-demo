package com.springdemo.project.controller;


import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Product;

import com.springdemo.project.mapper.ProductMapperService;
import com.springdemo.project.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapperService productMapperService;

    private String addProductPage = "addproduct";

    @GetMapping("/add")
    public String get(Model model)
    {
        model.addAttribute("product", new Product());
        return addProductPage;
    }

    @PostMapping("/add")
    public String post(@Valid @ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file , Errors errors, Model model)  throws IOException {
        addProductPage = productService.saveProduct(productMapperService.convertToEntity(productDTO), file, errors);
        return  addProductPage;
    }

    @GetMapping()
    public String getProd(Model model)
    {
        List<ProductDTO> productList =  productService.getProducts();
        model.addAttribute("products", productList);
        return "admin-products";
    }

    @GetMapping("/update")
    public String update(@RequestParam("prodId") int prodId, Model model)
    {
        Optional<ProductDTO> productDTO = productService.updateProduct(prodId);
        model.addAttribute("product", productDTO);
        return addProductPage;
    }

    @GetMapping("/delete")
    public  String delete(@RequestParam("prodId") int prodId)
    {
        productService.deleteProduct(prodId);
        return "redirect:/admin/products";
    }

}
