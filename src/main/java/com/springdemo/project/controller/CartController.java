package com.springdemo.project.controller;


import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.CartCost;
import com.springdemo.project.entity.User;

import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @GetMapping()
    public String get(HttpServletRequest httpServletRequest, Model model)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user2 = userRepository.findByUsername(username);

        CartCost cartCost =  cartService.getCartItemsForUser(user2);
        model.addAttribute("items",cartCost.getTotalItems());
        model.addAttribute("products", cartCost.getcartItems());
        model.addAttribute("total", cartCost.getTotalCost());
        return  "cart";
    }

    @PostMapping()
    public String post(HttpServletRequest httpServletRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String id = httpServletRequest.getParameter("id");
        User user = userRepository.findByUsername(username);

        Cart cart = cartService.getCartItem(user, Integer.parseInt(id));
        cartService.addToCart(cart, username, id);

        return "redirect:/";
    }


    @PostMapping("/delete")
    public String delete(HttpServletRequest httpServletRequest)
    {
        String id = httpServletRequest.getParameter("id");
        cartService.deleteById(Integer.parseInt(id));
        return "redirect:/cart";
    }

    @PostMapping("/reset")
    public  String clearCart(HttpServletRequest httpServletRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        cartService.deleteCartItemsForUser(user);

        return "redirect:/cart";
    }
}
