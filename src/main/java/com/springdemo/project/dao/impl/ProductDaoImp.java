package com.springdemo.project.dao.impl;

import com.springdemo.project.dao.ProductDao;
import com.springdemo.project.entity.Product;
import com.springdemo.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ProductDaoImp implements ProductDao {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(int id) {
        return   productRepository.findById(id);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
