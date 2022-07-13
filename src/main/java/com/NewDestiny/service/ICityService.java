package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.CityDto;

import java.util.List;



public interface ICityService {

    CityDto save(CityDto cityDto);
    List<CityDto> findAll();
    CityDto findById(Long id);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(CityDto cityDto);

}

