package com.springdemo.project.service;

import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import com.springdemo.project.repository.CartRepository;
import com.springdemo.project.services.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestCartService {
    @Autowired
    CartService cartService;
    @MockBean
    CartRepository cartRepo;

    @Test
    void getCartItems( ){
        User userEntity = new User("Sreeja","sreeja",1);
        when(cartRepo.findByUsername(userEntity)).thenReturn(Stream.of(new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8)).collect(Collectors.toList()));
        assertEquals(1, cartService.getCartItemsForUser(userEntity).getTotalItems());
    }

    @Test
    void  getCartItem(){
        User userEntity = new User("Sreeja","sreeja",1);
        Cart cartEntity = new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        when(cartRepo.findByUsernameAndId(userEntity, 1)).thenReturn(cartEntity);
        assertEquals(cartEntity, cartService.getCartItem(userEntity, 1));
    };

    @Test
    void addToCart(){
        User userEntity = new User("Sreeja","sreeja",1);
        Cart cartEntity = new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartService.addToCart(cartEntity,"Sreeja", String.valueOf(1));
        verify(cartRepo, times(1)).save(cartEntity);
    }

    @Test
    void deleteById()
    {
        User userEntity = new User("Sreeja","sreeja",1);
        Cart cartEntity = new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartService.deleteById(cartEntity.getId());
        verify(cartRepo, times(1)).deleteById(cartEntity.getId());
    }

    @Test
    void deleteCartItemsForUser() {
        User userEntity = new User("Sreeja","sreeja",1);
        Cart cartEntity = new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartService.deleteCartItemsForUser(userEntity);
        verify(cartRepo, times(1)).deleteByUserId(userEntity);
    }
}
