package com.NewDestiny.controller;


import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.CityDto;
import com.NewDestiny.service.implementation.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin ("*")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCity(@RequestBody CityDto cityDto) {
        cityService.save(cityDto);
        return ResponseEntity.ok(" La Ciudad se agrego con éxito");
    }

    @GetMapping("/list")
    public List<CityDto> listCities() {
        return cityService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        CityDto cityResponse = cityService.findById(id);
        if (cityResponse!=null){
            return ResponseEntity.ok(cityResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la ciudad con el id: " + id + " en la base de datos. Por favor ingrese un id válido");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        cityService.deleteById(id);
        return ResponseEntity.ok("Se elimino la ciudad correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CityDto cityDto) {
        cityDto.setId(id);
        cityService.update(cityDto);
        return ResponseEntity.ok("Se actualizo la ciudad correctamente");
    }
}
