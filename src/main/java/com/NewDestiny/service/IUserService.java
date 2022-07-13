package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.UserDto;
import com.NewDestiny.model.dto.UserModelDto;
import com.NewDestiny.model.entity.UserEntity;

import java.util.List;

public interface IUserService {
    UserDto save(UserDto userDto);
    List<UserDto> findAll();
    UserDto findById(Long id);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(UserDto userDto);
    UserEntity registerUser(UserModelDto userModelDto);

}
