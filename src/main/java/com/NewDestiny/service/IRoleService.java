package com.NewDestiny.service;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.RoleDto;

import java.util.List;

public interface IRoleService {

    RoleDto save(RoleDto roleDto);
    List<RoleDto> findAll();
    RoleDto findById(Long id);
    void deleteById(Long id) throws ResourceNotFoundException;
    void update(RoleDto RoleDto);

}
