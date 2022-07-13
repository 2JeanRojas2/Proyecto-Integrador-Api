package com.NewDestiny.controller;


import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ProductDto;
import com.NewDestiny.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin ("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return ResponseEntity.ok(" El Alojamiento se agrego con éxito");
    }

    @GetMapping("/list")
    public List<ProductDto> listProducts() {
        return productService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProductDto productResponse = productService.findById(id);
        if (productResponse != null) {
            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el alojamiento con el id: " + id + " en la base de datos. Por favor ingrese un id válido");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        productService.deleteById(id);
        return ResponseEntity.ok("Se elimino el alojamiento correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        productService.update(productDto);
        return ResponseEntity.ok("Se actualizo el alojamiento correctamente");
    }

    @GetMapping("/filter/products/{categoryId}")
    public List<ProductDto> listProductsByCategory(@PathVariable Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/filter/product/{cityId}")
    public List<ProductDto> listProductsByCity(@PathVariable Long cityId) {
        return productService.findByCityId(cityId);
    }

    @GetMapping("/filter/product/{startDate}/{endDate}")
    public List<ProductDto> listProductsBySchedules(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate) {
        return productService.findBySchedule(startDate, endDate);
    }







}
