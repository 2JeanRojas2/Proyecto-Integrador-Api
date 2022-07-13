package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ImageDto;
import com.NewDestiny.model.entity.Image;
import com.NewDestiny.repository.ImageRepository;
import com.NewDestiny.service.IImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public ImageDto save(ImageDto imageDto) {
        Image imageToSave = objectMapper.convertValue(imageDto, Image.class);
        imageRepository.save(imageToSave);
        return imageDto;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        ImageDto imageFound = findById(id);
        if (imageFound != null)
            imageRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe la imagen con el id = "+id+".Ingrese un id correcto por favor" );
    }

    @Override
    public void update(ImageDto imageDto) {
        Image modifiedImage = objectMapper.convertValue(imageDto, Image.class);
        imageRepository.save(modifiedImage);
    }

    @Override
    public List<ImageDto> findAll() {
        List<Image> images = imageRepository.findAll();
        List<ImageDto> imagesDto = new ArrayList<>();

        for(Image image: images){
            imagesDto.add(objectMapper.convertValue(image, ImageDto.class));
        }

        return imagesDto;

    }

    @Override
    public ImageDto findById(Long id) {
        ImageDto imageFound = objectMapper.convertValue(imageRepository.findById(id), ImageDto.class);
        return imageFound;
    }
}
