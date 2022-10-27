package com.springdemo.project.controller;


import com.springdemo.project.entity.User;
import com.springdemo.project.repository.UserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestSignUpController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepo;

    @Test
    void testSignup() throws Exception {
        mockMvc.perform(get("/signup")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    void saveUser() throws Exception {
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.ALL)
                        .param("username", "Sreeja")
                        .param("password","sreeja")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testUserExists() throws Exception {
        when(userRepo.findByUsername("Sreeja")).thenReturn(new User("Sreeja","sreeja",1));
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.ALL)
                        .param("username", "Sreeja")
                        .param("password","sreeja")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}




