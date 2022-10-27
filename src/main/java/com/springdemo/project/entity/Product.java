package com.springdemo.project.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;



    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title can not be empty!!")
    @Column(name = "title")
    private String title;



    @Column(name = "image_url")
    private String imageUrl;

    @NotNull(message = "Price cannot be null")
    @Min(value=0, message="Price must be greater than 0")
    @Column(name = "price")
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
