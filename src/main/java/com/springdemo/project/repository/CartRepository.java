package com.springdemo.project.repository;

import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select u from Cart u where u.user= ?1 and u.prodId= ?2")
    Cart findByUsernameAndId(User user, int id);

    @Query("select u from Cart u where u.user= ?1")
    List<Cart> findByUsername(User user);
    @Modifying
    @Query("delete from Cart u where u.user= ?1")
    void deleteByUserId(User user);
}


