package com.springdemo.project.services.impl;

import com.springdemo.project.dao.CartDao;
import com.springdemo.project.dto.CartDTO;
import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.CartCost;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import com.springdemo.project.mapper.CartMapperService;
import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.CartService;
import com.springdemo.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapperService cartMapperService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    CartDao cartDao;

    @Override
    @Transactional
    public CartCost getCartItemsForUser(User user) {
        List<Cart> cartList = cartDao.getCartItemsForUser(user);
        List<CartDTO> cartDTOs = new ArrayList<>();
        double total  = 0;
        int items= 0;
        for(Cart cart : cartList)
        {
            total += cart.getQuantity() * cart.getPrice();
            items += cart.getQuantity();
            cartDTOs.add(cartMapperService.convertToDTO(cart));
        }
        return new CartCost(cartDTOs,total,items);
    }

    @Override
    @Transactional
    public Cart getCartItem(User user, int id) {
        return cartDao.getCartItem(user,id);
    }

    @Override
    @Transactional
    public void addToCart(Cart cartInstance, String username, String id) {
        if(cartInstance == null)
        {
            Product product = productService.getProductById(Integer.parseInt(id));

            Cart cart = new Cart();
            User user = userRepository.findByUsername(username);
            cart.setUser(user);
            cart.setProdId(product.getId());
            cart.setPrice(product.getPrice());
            cart.setQuantity(1);
            cart.setTitle(product.getTitle());
            cart.setImageUrl(product.getImageUrl());

            cartDao.addToCart(cart);        }
        else {
            cartInstance.setQuantity(cartInstance.getQuantity() + 1);
            cartDao.addToCart(cartInstance);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
cartDao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCartItemsForUser(User user) {
        cartDao.deleteCartItemsForUser(user);
    }

}
