package com.springdemo.project.mapper;

import com.springdemo.project.dto.ProductDTO;
import com.springdemo.project.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service()
public class ProductMapperService {
    private ModelMapper modelMapper = new ModelMapper();

    public ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product convertToEntity(ProductDTO productDTO){
        try{
            log.info(">>>>> INSIDE MAPPER SERVICE: convertToEntity productdto Try block");
            return modelMapper.map(productDTO, Product.class);
        }
        catch (NullPointerException ne){
            log.info(">>>>> INSIDE MAPPER SERVICE: NULLPOINTEREXCEPTION productdto catch block");
            throw new NullPointerException("null pointer in job convertToEntity");
        }
    }
}
