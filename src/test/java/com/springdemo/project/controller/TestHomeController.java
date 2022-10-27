package com.springdemo.project.controller;

import com.springdemo.project.dto.CartDTO;
import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import com.springdemo.project.mapper.CartMapperService;
import com.springdemo.project.mapper.ProductMapperService;
import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.CartService;
import com.springdemo.project.services.ProductService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
 class TestHomeController {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductService productService;
    @MockBean
    private UserRepository userRepo;

    @MockBean
    private CartService cartService;

    @MockBean
    private ProductMapperService productMapperService;

    @MockBean
    private CartMapperService cartMapperService;

    @Test
    @WithMockUser(username = "Sreeja", password = "sreeja", roles = "user")
      void test() throws Exception {
        when(userRepo.findByUsername("Sreeja")).thenReturn(new User("Sreeja", "sreeja", 1));
        mockMvc.perform(get("/")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "admin")
    void homePageRedirect() throws Exception {
        when(userRepo.findByUsername("admin")).thenReturn(new User("admin", "123", 1));
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/**/*admin/products"));
    }

    @Test
    void getAccessDenied() throws Exception {
        mockMvc.perform(get("/access-denied")).andExpect(status().is(200))
                .andExpect(view().name("access-denied"));
    }

}
