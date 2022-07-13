package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ProductDto;


import java.util.List;

public interface IProductService {

    ProductDto save(ProductDto productDto);
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(ProductDto productDto);

}

