package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ImageDto;

import java.util.List;

public interface IImageService {
    ImageDto save(ImageDto imageDto);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(ImageDto imageDto);
    List<ImageDto> findAll();
    ImageDto findById(Long id);
}
