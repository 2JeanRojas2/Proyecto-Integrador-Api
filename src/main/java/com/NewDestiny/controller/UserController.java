package com.NewDestiny.controller;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.UserDto;
import com.NewDestiny.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin ("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok(" El usuario se agrego con éxito");
    }

    @GetMapping("/list")
    public List<UserDto> listUsers() {
        return userService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        UserDto userResponse = userService.findById(id);
        if(userResponse!=null){
            return ResponseEntity.ok(userResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con el id: " + id + " en la base de datos. Por favor ingrese un id válido");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        userService.deleteById(id);
        return ResponseEntity.ok("Se elimino el usuario correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userService.update(userDto);
        return ResponseEntity.ok("Se actualizo el usuario correctamente");
    }

}
