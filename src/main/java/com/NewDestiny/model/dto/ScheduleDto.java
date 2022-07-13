package com.NewDestiny.model.dto;


import com.NewDestiny.model.entity.Product;
import com.NewDestiny.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDto {

    private Long id;
    private LocalTime startTime;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonIncludeProperties(value = {"id"})
    private Product product;
    @JsonIncludeProperties(value = {"id"})
    private UserEntity userEntity;

}


