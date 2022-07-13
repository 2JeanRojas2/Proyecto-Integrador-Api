package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.FeatureDto;

import java.util.List;

public interface IFeatureService {
    FeatureDto save(FeatureDto featureDto);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(FeatureDto featureDto);
    List<FeatureDto> findAll();
    FeatureDto findById(Long id);

}
