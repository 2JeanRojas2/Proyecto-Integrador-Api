package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ImageDto;
import com.NewDestiny.model.dto.ProductDto;
import com.NewDestiny.model.entity.Image;
import com.NewDestiny.model.entity.Product;
import com.NewDestiny.repository.IProductRepository;
import com.NewDestiny.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ImageService imageService;

    @Override
    public ProductDto save(ProductDto productDto) {
        Product productToSave = objectMapper.convertValue(productDto, Product.class);
        Product product =  productRepository.save(productToSave);
        List<ImageDto> listImages = imageService.findAll();
        for (ImageDto imageDto : listImages){
            if(!imageDto.getProduct().equals("")){
                imageDto.setProduct(product);
            }
        }
        return productDto;
    }



    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productsDto = new ArrayList<>();

        for(Product product: products){
            productsDto.add(objectMapper.convertValue(product, ProductDto.class));
        }

        return productsDto;
    }

    @Override
    public ProductDto findById(Long id) {
        ProductDto productFound = objectMapper.convertValue(productRepository.findById(id), ProductDto.class);
        return productFound;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        ProductDto productFound = findById(id);
        if (productFound != null)
            productRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el alojamiento con el id= "+id+".Ingrese un id correcto por favor" );
    }

    @Override
    public void update(ProductDto productDto) {
        Product modifiedProduct = objectMapper.convertValue(productDto, Product.class);
        productRepository.save(modifiedProduct);
    }

    public List<ProductDto> findByCategoryId(Long categoryId){
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        List<ProductDto> productsDto = new ArrayList<>();

        for(Product product: products){
            productsDto.add(objectMapper.convertValue(product, ProductDto.class));
        }
        return productsDto;
    }

    public List<ProductDto> findByCityId(Long cityId){
        List<Product> products = productRepository.findAllByCityId(cityId);
        List<ProductDto> productsDto = new ArrayList<>();

        for(Product product: products){
            productsDto.add(objectMapper.convertValue(product, ProductDto.class));
        }
        return productsDto;
    }

    public List<ProductDto> findBySchedule(LocalDate startDate, LocalDate endDate){
        List<Product> products = productRepository.findAllBySchedulesStartDateAndSchedulesEndDate(startDate,endDate);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products){
            productDtos.add(objectMapper.convertValue(product, ProductDto.class));
        }
        return productDtos;
    }


}


