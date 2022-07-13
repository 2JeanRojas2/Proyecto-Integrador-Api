package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.CategoryDto;

import java.util.List;



public interface ICategoryService {

    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(CategoryDto categoryDto);
    CategoryDto save(CategoryDto categoryDto);

}

