package com.springdemo.project.services;

import com.springdemo.project.entity.User;
import org.springframework.validation.Errors;

public interface UserService {
    String saveUser(User user, Errors errors);
}
