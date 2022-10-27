package com.springdemo.project.mapper;

import com.springdemo.project.dto.CartDTO;
import com.springdemo.project.entity.Cart;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service()
public class CartMapperService {
    private ModelMapper modelMapper = new ModelMapper();
    public CartDTO convertToDTO(Cart cart) {
         return modelMapper.map(cart,CartDTO.class);
    }
}
