package com.springdemo.project.dto;

import com.springdemo.project.entity.User;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartDTO {
    private Integer id;
    private User user;
    private int quantity;
    private int prodId;
    private String title;
    private String imageUrl;
    private Double price;
}
