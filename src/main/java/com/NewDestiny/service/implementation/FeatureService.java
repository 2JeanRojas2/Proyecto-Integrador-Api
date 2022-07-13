package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.FeatureDto;
import com.NewDestiny.model.entity.Feature;
import com.NewDestiny.repository.IFeatureRepository;
import com.NewDestiny.service.IFeatureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService implements IFeatureService {
    @Autowired
    IFeatureRepository featureRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public FeatureDto save(FeatureDto featureDto) {
        Feature featureToSave = objectMapper.convertValue(featureDto, Feature.class);
        featureRepository.save(featureToSave);
        return featureDto;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        FeatureDto featureFound = findById(id);
        if (featureFound != null)
            featureRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe la categoria con el id= "+id+".Ingrese un id correcto por favor" );
    }

    @Override
    public void update(FeatureDto featureDto) {
        Feature modifiedFeature = objectMapper.convertValue(featureDto, Feature.class);
        featureRepository.save(modifiedFeature);
    }

    @Override
    public List<FeatureDto> findAll() {
        List<Feature> features = featureRepository.findAll();
        List<FeatureDto> featuresDto = new ArrayList<>();

        for(Feature feature: features){
            featuresDto.add(objectMapper.convertValue(feature, FeatureDto.class));
        }

        return featuresDto;
    }

    @Override
    public FeatureDto findById(Long id) {
        FeatureDto featureFound = objectMapper.convertValue(featureRepository.findById(id), FeatureDto.class);
        return featureFound;
    }
}
