package com.springdemo.project.controller;


import com.springdemo.project.dto.CartDTO;
import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.CartCost;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import com.springdemo.project.mapper.CartMapperService;
import com.springdemo.project.repository.CartRepository;
import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.CartService;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
  class TestCartController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartMapperService cartMapperService;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private CartRepository cartRepo;

    @Test
    @WithMockUser(username = "Sreeja", password = "sreeja", roles = "user")
     void getCart() throws Exception {
        User userEntity = new User("Sreeja","sreeja",1);
        CartDTO cartDTO1 = new CartDTO(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        CartDTO cartDTO2 = new CartDTO(2,userEntity,1,1,"bat","http://bat.png", 30.8);
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList.add(cartDTO1);
        cartDTOList.add(cartDTO2);
        when(cartService.getCartItemsForUser(userEntity)).thenReturn(new CartCost(cartDTOList,61.6, 2));
        when(userRepo.findByUsername("Sreeja")).thenReturn(userEntity);
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Sreeja", password = "sreeja", roles = "user")
     void postCartTest() throws Exception {
        User userEntity = new User("Sreeja","sreeja",1);
        Cart cartEntity = new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8);

        when(userRepo.findByUsername("Sreeja")).thenReturn(userEntity);
        when(cartService.getCartItem(userEntity,1)).thenReturn(cartEntity);

        mockMvc.perform(post("/cart")
                        .contentType(MediaType.ALL)
                        .param("id", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "Sreeja", password = "sreeja", roles = "user")
      void addToCart() throws Exception {
        User userEntity = new User("Sreeja","sreeja",1);
        when(userRepo.findByUsername("Sreeja")).thenReturn(userEntity);
        when(cartService.getCartItem(userEntity,1)).thenReturn(null);
        Product productEntity=new Product(1, "Book", "http:book.png", 20.5);

        when(productService.getProductById(1)).thenReturn(productEntity);
        mockMvc.perform(post("/cart")
                        .contentType(MediaType.ALL)
                        .param("id", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "Sreeja", password = "sreeja", roles = "user")
      void  deleteCart() throws Exception {
        User userEntity = new User("Sreeja","sreeja",1);
        Cart cartEntity = new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartService.deleteById(cartEntity.getId());
        mockMvc.perform(post("/cart/delete")
                        .contentType(MediaType.ALL)
                        .param("id", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "Sreeja", password = "sreeja", roles = "user")
    void  clearCart() throws Exception {
        User userEntity = new User("Sreeja","sreeja",1);
        cartService.deleteCartItemsForUser(userEntity);
        mockMvc.perform(post("/cart/reset")
                        .contentType(MediaType.ALL)
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
