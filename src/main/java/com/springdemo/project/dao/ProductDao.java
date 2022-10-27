package com.springdemo.project.dao;

import com.springdemo.project.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

   List<Product> getProducts();

   Product getProductById(int id);

   void saveProduct(Product product);

   //update

   Optional<Product> updateProduct(int id);


   //delete

   void deleteProduct(int id);

}
