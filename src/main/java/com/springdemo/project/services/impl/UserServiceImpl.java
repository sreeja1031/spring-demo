package com.springdemo.project.services.impl;


import com.springdemo.project.entity.User;
import com.springdemo.project.mapper.UserMapperService;
import com.springdemo.project.repository.UserRepository;
import com.springdemo.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapperService userMapperService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(User user, Errors errors) {
        if (errors.getErrorCount() > 0) {
            return "signup";
        }
        else {
            User account = userRepository.findByUsername(user.getUsername());
            if(account == null)
            {
                User userEntity = new User();
                userEntity.setUsername(user.getUsername());
                userEntity.setPassword(user.getPassword());
                userEntity.setEnabled(1);
                userRepository.save(userEntity);
                return "redirect:/signin";
            }
            else {
                errors.rejectValue("username","error","An account with this username already exists.");
                return "signup";
            }
        }
    }
}
