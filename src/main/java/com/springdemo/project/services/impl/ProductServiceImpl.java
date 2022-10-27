package com.springdemo.project.services.impl;

import com.springdemo.project.dao.ProductDao;
import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Product;
import com.springdemo.project.exception.NotFoundException;
import com.springdemo.project.mapper.ProductMapperService;
import com.springdemo.project.services.ProductService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }


    @Autowired
    private ProductMapperService productMapperService;


    @Override
    @Transactional
    public String saveProduct(Product product, MultipartFile file, Errors errors) throws IOException {
        if(errors.hasFieldErrors() )
        {
            return "addproduct";
        }
        if(file.isEmpty())
        {
            errors.rejectValue("imageUrl","error","Upload an image.");
            return "addproduct";
        }
        byte[] image = Base64.encodeBase64(file.getBytes());
        String result = new String(image);
        String res = "data:image/png;base64," + result;
        product.setImageUrl(res);

        productDao.saveProduct(product);
        return  "redirect:/admin/products";
    }

    @Override
    @Transactional
    public List<ProductDTO> getProducts() {
        List<Product> productList = productDao.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : productList)
        {
            productDTOs.add(productMapperService.convertToDTO(product));
        }

        return productDTOs;
    }

    @Override
    @Transactional
    public Optional<ProductDTO> updateProduct(int id) {
        Optional<Product> productEntity = productDao.updateProduct(id);
        Product product;
        if(productEntity.isPresent())
        {
            product = productEntity.get();
        }
        else {
            throw new NotFoundException("Could not find product with id - " + id);
        }
        return Optional.ofNullable(productMapperService.convertToDTO(product));
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }
}
