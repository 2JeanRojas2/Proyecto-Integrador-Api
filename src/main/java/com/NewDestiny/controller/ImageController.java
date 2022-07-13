package com.NewDestiny.controller;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ImageDto;
import com.NewDestiny.service.implementation.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/images")
@CrossOrigin ("*")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<String> saveImage(@RequestBody ImageDto imageDto) {
        imageService.save(imageDto);
        return ResponseEntity.ok(" La imagen se agrego con éxito");
    }

    @GetMapping("/list")
    public List<ImageDto> listImages() {
        return imageService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ImageDto imageResponse = imageService.findById(id);
        if(imageResponse!=null){
            return ResponseEntity.ok(imageResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la imagen con el id: " + id + " en la base de datos. Por favor ingrese un id válido");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        imageService.deleteById(id);
        return ResponseEntity.ok("Se elimino la imagen correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ImageDto imageDto) {
        imageDto.setId(id);
        imageService.update(imageDto);
        return ResponseEntity.ok("Se actualizo la imagen correctamente");
    }

}
