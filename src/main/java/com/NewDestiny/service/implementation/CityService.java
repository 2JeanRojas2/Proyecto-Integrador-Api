package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.CityDto;
import com.NewDestiny.model.entity.City;
import com.NewDestiny.repository.ICityRepository;
import com.NewDestiny.service.ICityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {
    @Autowired
    ICityRepository cityRepository;
    @Autowired
    ObjectMapper objectMapper;


    @Override
    public CityDto save(CityDto cityDto) {
        City cityToSave = objectMapper.convertValue(cityDto, City.class);
        cityRepository.save(cityToSave);
        return cityDto;
    }

    @Override
    public List<CityDto> findAll() {
        List<City> cities = cityRepository.findAll();
        List<CityDto> citiesDto = new ArrayList<>();

        for(City city: cities){
            citiesDto.add(objectMapper.convertValue(city, CityDto.class));
        }

        return citiesDto;
    }

    @Override
    public CityDto findById(Long id) {
        CityDto cityFound = objectMapper.convertValue(cityRepository.findById(id), CityDto.class);
        return cityFound;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        CityDto cityFound = findById(id);
        if (cityFound != null)
            cityRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe la ciudad con el id= "+id+".Ingrese un id correcto por favor" );
    }

    @Override
    public void update(CityDto cityDto) {
        City modifiedCity = objectMapper.convertValue(cityDto, City.class);
        cityRepository.save(modifiedCity);
    }
}

