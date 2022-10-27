package com.springdemo.project;

import com.springdemo.project.controller.*;
import com.springdemo.project.dao.CartDao;
import com.springdemo.project.dao.ProductDao;
import com.springdemo.project.entity.Authority;
import com.springdemo.project.entity.Cart;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import com.springdemo.project.repository.CartRepository;
import com.springdemo.project.repository.ProductRepository;
import com.springdemo.project.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private CartDao cartDao;

	@Autowired
	CartController cartController;

	@Autowired
	AdminController adminController;
	@Autowired
	HomeController homeController;
	@Autowired
	SignInController signinController;
	@Autowired
	SignUpController signupController;

	@MockBean
	CartRepository cartRepository;

	@MockBean
	ProductDao productDao;

	@MockBean
	private ProductService productService;

	@Test
	void contextLoads() {
		assertThat(cartController).isNotNull();
		assertThat(adminController).isNotNull();
		assertThat(homeController).isNotNull();
		assertThat(signinController).isNotNull();
		assertThat(signupController).isNotNull();
	}

	@Mock
	User user;

	@Test
	void getUsername() {
		user = new User("username","password", 1);
		assertEquals("username", user.getUsername());
	}

	@Test
	void getPassword() {
		user = new User("username","password", 1);
		assertEquals("password", user.getPassword());
	}

	@Test
	void getEnabled() {
		user = new User("username","password",1);
		user.setEnabled(1);
		assertEquals(1, user.getEnabled());
	}

	@Test
	void setUsername() {
		user = new User("username","password",1);
		user.setUsername("sreeja");
		assertEquals("sreeja", user.getUsername());
	}

	@Test
	void setPassword() {
		user = new User("username","password",1);
		user.setPassword("test123");
		assertEquals("test123", user.getPassword());
	}

	@Test
	void setEnabled() {
		user = new User("username","password",1);
		user.setEnabled(1);
		assertEquals(1, user.getEnabled());
	}
	@Mock
	Authority authority;

	@Test
	void getUserName() {
		authority = new Authority("username","authority");
		assertEquals("username", authority.getUsername());
	}

	@Test
	void getAuthorty() {
		authority = new Authority("username","authority");
		assertEquals("authority", authority.getAuth());
	}

	@Test
	void setUserName() {
		authority = new Authority("username","authority");
		authority.setUsername("sreeja");
		assertEquals("sreeja", authority.getUsername());
	}

	@Test
	void setAuthority() {
		authority = new Authority("username","authority");
		authority.setAuth("test123");
		assertEquals("test123", authority.getAuth());
	}
	@Mock
	Product product;

	@Test
	void getProductId() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		assertEquals(Integer.valueOf(1), product.getId());
	}

	@Test
	void getProductTitle() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		assertEquals("Book", product.getTitle());
	}

	@Test
	void getProductImageUrl() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		assertEquals("http:book.png", product.getImageUrl());
	}

	@Test
	void getProductPrice() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		assertEquals(Double.valueOf(20.5), product.getPrice());
	}

	@Test
	void setProductId() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		product.setId(1);
		assertEquals(Integer.valueOf(1), product.getId());
	}

	@Test
	void setProductTitle() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		product.setTitle("Book");
		assertEquals("Book", product.getTitle());
	}

	@Test
	void setProductImageUrl() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		product.setImageUrl("http:book.png");
		assertEquals("http:book.png", product.getImageUrl());
	}

	@Test
	void setProductPrice() {
		product = new Product(1, "Book", "http:book.png", 20.5);
		product.setPrice(20.5);
		assertEquals(Double.valueOf(20.5), product.getPrice());
	}
	@Mock
	Cart cart;

	@Test
	void setCartId() {
		cart = new Cart(1,user,1,1,"bat","http://bat.png", 30.8);
		cart.setId(1);
		assertEquals(Integer.valueOf(1), cart.getId());
	}

	@Test
	void getCartItemsDao(){
		User userEntity = new User("mary123","mary",1);
		when(cartRepository.findByUsername(userEntity)).thenReturn(Stream.of(new Cart(1,userEntity,1,1,"bat","http://bat.png", 30.8)).collect(Collectors.toList()));
		assertEquals(1, cartDao.getCartItemsForUser(userEntity).size());
	}

}
