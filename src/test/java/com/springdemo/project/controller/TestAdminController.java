package com.springdemo.project.controller;

import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import com.springdemo.project.mapper.ProductMapperService;
import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestAdminController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private ProductMapperService productMapperService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "admin")
    void addProductPage() throws Exception {
        mockMvc.perform(get("/admin/products/add")).andExpect(status().is(200))
                .andExpect(view().name("addproduct"));
    }
    @Test
    @WithMockUser(username = "admin", password = "123", roles = "admin")
    void testAdmin() throws Exception {
        when(userRepo.findByUsername("admin")).thenReturn(new User("admin", "123", 1));

        mockMvc.perform(get("/admin/products")
                        .contentType(MediaType.ALL)
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "admin")
    void getProducts() throws Exception {
        User userEntity = new User("admin","123",1);
        when(userRepo.findByUsername("admin")).thenReturn(userEntity);
        mockMvc.perform(get("/admin/products"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "admin")
    void  deleteProduct() throws Exception {
        Product productEntity=new Product(1, "Book", "http:book.png", 20.5);
        productService.deleteProduct(productEntity.getId());
        mockMvc.perform(get("/admin/products/delete")
                        .contentType(MediaType.ALL)
                        .param("prodId", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "admin")
    void  updateProduct() throws Exception {

        Product productEntity=new Product(1, "Book", "http:book.png", 20.5);
        productService.updateProduct(productEntity.getId());
        mockMvc.perform(get("/admin/products/update")
                        .contentType(MediaType.ALL)
                        .param("prodId", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
