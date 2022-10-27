package com.springdemo.project.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Data
public class User {
    @Id
    @NotNull(message = "Username can not be null!!")
    @NotEmpty(message = "Username can not be empty!!")
    @Column(name = "username")
    private String username;


    @NotNull(message = "Password can not be null!!")
    @NotEmpty(message = "Password can not be empty!!")
    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private int enabled;
}
