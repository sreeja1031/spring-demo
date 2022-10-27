package com.springdemo.project.services;

import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Product;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getProductById(int id);

    String saveProduct(Product product, MultipartFile file, Errors errors) throws IOException;

    //get
    List<ProductDTO> getProducts();

    //update
    Optional<ProductDTO> updateProduct(int id);

    //delete
    void deleteProduct(int id);
}
