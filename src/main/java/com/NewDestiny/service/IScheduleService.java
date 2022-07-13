package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ScheduleDto;

import java.util.List;

public interface IScheduleService {
    ScheduleDto save(ScheduleDto ScheduleDto);
    List<ScheduleDto> findAll();
    ScheduleDto findById(Long id);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(ScheduleDto ScheduleDto);
}
