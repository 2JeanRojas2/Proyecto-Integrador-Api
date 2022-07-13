package com.NewDestiny.model.dto;

import com.NewDestiny.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModelDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;
    private String matchingPassword;
    @JsonIncludeProperties(value = {"id"})
    private Role role;
}
