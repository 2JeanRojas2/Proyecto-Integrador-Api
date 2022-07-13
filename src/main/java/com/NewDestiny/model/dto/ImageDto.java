package com.NewDestiny.model.dto;
import com.NewDestiny.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String title;
    private String url;
    @JsonIncludeProperties(value = {"id"})
    private Product product;

}
