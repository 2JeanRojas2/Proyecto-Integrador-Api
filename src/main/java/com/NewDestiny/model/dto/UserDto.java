package com.NewDestiny.model.dto;

import com.NewDestiny.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;
    private boolean enabled;

    @JsonIncludeProperties(value = {"id"})
    private Role role;

}
