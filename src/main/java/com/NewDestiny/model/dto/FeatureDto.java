package com.NewDestiny.model.dto;
import lombok.Data;



@Data
public class FeatureDto {
    private Long id;
    private boolean kitchen;
    private boolean freeParking;
    private boolean tv;
    private boolean pool;
    private boolean airConditioning;
    private boolean petsAllowed;
    private boolean wifi;
}
