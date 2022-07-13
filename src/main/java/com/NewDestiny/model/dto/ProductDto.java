package com.NewDestiny.model.dto;

import com.NewDestiny.model.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import java.util.List;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String shortDescription;
    private String cancellationPolicy;
    private String houseRules;
    private String healthSafety;
    private String rating;
    private String address;

    @JsonIncludeProperties(value = {"id", "name"})
    private City city;
    @JsonIncludeProperties(value = {"id", "name"})
    private Category category;
    @JsonIgnoreProperties(value = {"product"})
    private List<Image> images;
    private Feature feature;
}
