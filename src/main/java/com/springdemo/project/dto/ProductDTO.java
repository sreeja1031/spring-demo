package com.springdemo.project.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private Integer id;
    private String title;
    private String imageUrl;
    private Double price;
}