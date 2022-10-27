package com.springdemo.project.controller;


import com.springdemo.project.dto.UserDTO;
import com.springdemo.project.entity.User;
import com.springdemo.project.mapper.UserMapperService;
import com.springdemo.project.repository.UserRepository;

import com.springdemo.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperService userMapperService;

    @Autowired
    private UserService userService;

    private String page = "signup";

    @GetMapping()
    public  String get(UserDTO user, Model model){
        model.addAttribute("user", new User());
        return page;
    }


    @PostMapping()
    public String post(@Valid  @ModelAttribute("user") UserDTO userDTO, Errors errors, Model model) throws  NullPointerException
    {
        page = userService.saveUser(userMapperService.convertToEntity(userDTO), errors);
        return page;
    }
}


