package com.springdemo.project.mapper;

import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.dto.UserDTO;
import com.springdemo.project.entity.Product;
import com.springdemo.project.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@Service()
public class UserMapperService {

    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO){
        try{
            log.info(">>>>> INSIDE MAPPER SERVICE: convertToEntity userdto Try block");
            return modelMapper.map(userDTO, User.class);
        }
        catch (NullPointerException ne){
            log.info(">>>>> INSIDE MAPPER SERVICE: NULLPOINTEREXCEPTION userdto catch block");
            throw new NullPointerException("null pointer in job convertToEntity");
        }
    }
}
