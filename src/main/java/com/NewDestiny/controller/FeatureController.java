package com.NewDestiny.controller;


import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.FeatureDto;
import com.NewDestiny.service.implementation.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/features")
@CrossOrigin ("*")
public class FeatureController {
    @Autowired
    private FeatureService featureService;

    @PostMapping("/save")
    public ResponseEntity<String> saveFeature(@RequestBody FeatureDto featureDto) {
        featureService.save(featureDto);
        return ResponseEntity.ok(" La Característica se agrego con éxito");
    }

    @GetMapping("/list")
    public List<FeatureDto> listFeatures() {
        return featureService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        FeatureDto featureResponse = featureService.findById(id);
        if (featureResponse!=null) {
            return ResponseEntity.ok(featureResponse);
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la característica con el id: " + id + " en la base de datos. Por favor ingrese un id válido");}
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        featureService.deleteById(id);
        return ResponseEntity.ok("Se elimino la característica correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody FeatureDto featureDto) {
        featureDto.setId(id);
        featureService.update(featureDto);
        return ResponseEntity.ok("Se actualizo la característica correctamente");
    }

}


