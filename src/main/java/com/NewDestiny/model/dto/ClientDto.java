package com.NewDestiny.model.dto;

import com.NewDestiny.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class ClientDto  {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;

    private Role role;
    private List<ScheduleDto> schedules;
}
