package com.springdemo.project.repository;

import com.springdemo.project.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

}
