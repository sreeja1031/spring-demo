package com.springdemo.project.entity;

import com.springdemo.project.dto.CartDTO;

import java.util.List;

public class CartCost {
    private List<CartDTO> cartDTOS;
    private double totalCost;
    private int totalItems;

    public CartCost(List<CartDTO> cartDtoList, double totalCost, int totalItems) {
        this.cartDTOS = cartDtoList;
        this.totalCost = totalCost;
        this.totalItems = totalItems;
    }

    public List<CartDTO> getcartItems() {
        return cartDTOS;
    }

    public void setCartItems(List<CartDTO> cartDtoList) {
        this.cartDTOS = cartDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
