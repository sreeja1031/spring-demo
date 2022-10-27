package com.springdemo.project.dao;

import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.User;

import java.util.List;

public interface CartDao {

    List<Cart> getCartItemsForUser(User user);


    //get a cart item

    Cart getCartItem(User user, int id);

    //save cart

    void addToCart(Cart cart);


    //delete cart item

    void deleteById(int id);


    void deleteCartItemsForUser(User user);
}
