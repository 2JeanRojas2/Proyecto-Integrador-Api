package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.RoleDto;
import com.NewDestiny.model.entity.Role;
import com.NewDestiny.repository.IRoleRepository;
import com.NewDestiny.service.IRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role roleToSave = objectMapper.convertValue(roleDto, Role.class);
        roleRepository.save(roleToSave);
        return roleDto;
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> rolesDto = new ArrayList<>();

        for(Role role: roles){
            rolesDto.add(objectMapper.convertValue(role, RoleDto.class));
        }

        return rolesDto;
    }

    @Override
    public RoleDto findById(Long id) {
        RoleDto roleFound = objectMapper.convertValue(roleRepository.findById(id), RoleDto.class);
        return roleFound;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        RoleDto roleFound = findById(id);
        if (roleFound != null)
            roleRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el alojamiento con el id= "+id+".Ingrese un id correcto por favor" );
    }

    @Override
    public void update(RoleDto RoleDto) {
        Role modifiedRole = objectMapper.convertValue(RoleDto, Role.class);
        roleRepository.save(modifiedRole);
    }
}

