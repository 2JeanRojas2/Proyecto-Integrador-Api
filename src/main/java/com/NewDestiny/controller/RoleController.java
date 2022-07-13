package com.NewDestiny.controller;


import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.RoleDto;
import com.NewDestiny.service.implementation.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("roles")
@CrossOrigin ("*")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<String> saveRole(@RequestBody RoleDto roleDto) {
        roleService.save(roleDto);
        return ResponseEntity.ok(" El rol se agrego con éxito");
    }

    @GetMapping("/list")
    public List<RoleDto> listRoles() {
        return roleService.findAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        roleService.deleteById(id);
        return ResponseEntity.ok("Se elimino el rol correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        roleDto.setId(id);
        roleService.update(roleDto);
        return ResponseEntity.ok("Se actualizo el rol correctamente");
    }




}
