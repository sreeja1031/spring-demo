package com.springdemo.project.services;

import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.CartCost;
import com.springdemo.project.entity.User;

public interface CartService {

    CartCost getCartItemsForUser(User user);

    //get a cart item
    Cart getCartItem(User user, int id);

    //save cart
    void addToCart(Cart cart, String username, String id);


    //delete cart item
    void deleteById(int id);

    void deleteCartItemsForUser(User user);

}
