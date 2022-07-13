package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ProductDto;
import com.NewDestiny.model.dto.ScheduleDto;
import com.NewDestiny.model.entity.Schedule;
import com.NewDestiny.repository.IScheduleRepository;
import com.NewDestiny.service.IScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ScheduleDto save(ScheduleDto ScheduleDto) {
        Schedule ScheduleToSave = objectMapper.convertValue(ScheduleDto, Schedule.class);
        scheduleRepository.save(ScheduleToSave);
        return ScheduleDto;
    }

    @Override
    public List<ScheduleDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleDto> schedulesDos = new ArrayList<>();

        for(Schedule schedule: schedules){
            schedulesDos.add(objectMapper.convertValue(schedule, ScheduleDto.class));
        }

        return schedulesDos;

    }

    @Override
    public ScheduleDto findById(Long id) {
        ScheduleDto scheduleFound = objectMapper.convertValue(scheduleRepository.findById(id), ScheduleDto.class);
        return scheduleFound;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        ScheduleDto scheduleFound = findById(id);
        if (scheduleFound != null)
            scheduleRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el alojamiento con el id= "+id+".Ingrese un id correcto por favor" );
    }

    @Override
    public void update(ScheduleDto ScheduleDto) {
        Schedule modifiedSchedule = objectMapper.convertValue(ScheduleDto, Schedule.class);
        scheduleRepository.save(modifiedSchedule);
    }

    public List<ScheduleDto> findAllByUserId(Long userId){
        List<Schedule> schedules = scheduleRepository.findAllByUserEntityId(userId);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDtos.add(objectMapper.convertValue(schedule, ScheduleDto.class));
        }

        return scheduleDtos;
    }

    public List<ScheduleDto> findAllByProductId(Long productId){
        List<Schedule> schedules = scheduleRepository.findAllByProductId(productId);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();

        for(Schedule schedule: schedules){
            scheduleDtos.add(objectMapper.convertValue(schedule, ScheduleDto.class));
        }

        return scheduleDtos;
    }
}

