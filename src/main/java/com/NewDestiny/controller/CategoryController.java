package com.NewDestiny.controller;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.CategoryDto;
import com.NewDestiny.service.implementation.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
@CrossOrigin ("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return ResponseEntity.ok(" La Categoría se agrego con éxito");
    }

    @GetMapping("/list")
    public List<CategoryDto> listCategories(){
        return categoryService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        CategoryDto categoryResponse = categoryService.findById(id);
        if (categoryResponse!=null){
            return ResponseEntity.ok(categoryResponse);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la categoría con el id: " + id + " en la base de datos. Por favor ingrese un id válido");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        categoryService.deleteById(id);
        return ResponseEntity.ok("Se elimino la categoría correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        categoryService.update(categoryDto);
        return ResponseEntity.ok("Se actualizo la categoría correctamente");
    }

}
