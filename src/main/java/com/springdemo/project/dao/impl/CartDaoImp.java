package com.springdemo.project.dao.impl;

import com.springdemo.project.dao.CartDao;
import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.User;
import com.springdemo.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CartDaoImp implements CartDao {

    @Autowired
    CartRepository cartRepository;
    @Override
    public List<Cart> getCartItemsForUser(User user) {
        return cartRepository.findByUsername(user);
    }

    @Override
    public Cart getCartItem(User user, int id) {
        return cartRepository.findByUsernameAndId(user,id);
    }

    @Override
    public void addToCart(Cart cart) {
cartRepository.save(cart);
    }

    @Override
    public void deleteById(int id) {

        cartRepository.deleteById(id);

    }

    @Override
    public void deleteCartItemsForUser(User user) {

        cartRepository.deleteByUserId(user);

    }
}
