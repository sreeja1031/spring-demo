package com.springdemo.project.service;

import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Product;
import com.springdemo.project.exception.NotFoundException;
import com.springdemo.project.mapper.ProductMapperService;
import com.springdemo.project.repository.ProductRepository;
import com.springdemo.project.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestProductService {
    @MockBean
    private ProductRepository productRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    ProductMapperService productMapperService;


    @Test
    void getProductById(){
        Product productEntity = new Product(1,"book", "http://book.png", 60.9);
        when(productRepo.getReferenceById(productEntity.getId())).thenReturn(productEntity);
        assertEquals(productEntity, productService.getProductById(productEntity.getId()));
    }

    @Test
    void testGetProducts(){
        when(productRepo.findAll()).thenReturn(Stream.of(new Product(1,"book","http://image.png",20.6),new Product(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2,productService.getProducts().size());
    }

    @Test
    void testUpdateProduct() {
        Product productEntity = new Product(3, "Book", "http:book.png", 20.5);
        Optional<Product> optionalProductEntity = Optional.of(productEntity);
        when(productRepo.findById(3)).thenReturn(optionalProductEntity);
        Optional<ProductDTO> productDTO = productService.updateProduct(productEntity.getId());
        Product product = new Product();
        if (productDTO.isPresent()) {
            product = productMapperService.convertToEntity(productDTO.get());
        }
        assertEquals(product, productEntity);
    }

    @Test
    void testDeleteProduct(){
        Product productEntity = new Product(3, "Book", "http:book.png", 20.5);
        productService.deleteProduct(productEntity.getId());
        verify(productRepo,times(1)).deleteById(productEntity.getId());

    }
}
