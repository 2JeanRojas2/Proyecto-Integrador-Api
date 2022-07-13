package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.repository.ICategoryRepository;
import com.NewDestiny.service.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NewDestiny.model.entity.Category;
import com.NewDestiny.model.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoriesDto = new ArrayList<>();

        for(Category category: categories){
            categoriesDto.add(objectMapper.convertValue(category, CategoryDto.class));
        }

        return categoriesDto;
    }

    @Override
    public CategoryDto findById(Long id) {
        CategoryDto categoryFound = objectMapper.convertValue(categoryRepository.findById(id), CategoryDto.class);
        return categoryFound;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        CategoryDto categoryFound = findById(id);
        if (categoryFound != null)
            categoryRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe la categoria con el id= "+id+".Ingrese un id correcto por favor" );

    }

    @Override
    public void update(CategoryDto categoryDto) {
        Category modifiedCategory = objectMapper.convertValue(categoryDto, Category.class);
        categoryRepository.save(modifiedCategory);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category categoryToSave = objectMapper.convertValue(categoryDto, Category.class);
        categoryRepository.save(categoryToSave);
        return categoryDto;
    }
}

