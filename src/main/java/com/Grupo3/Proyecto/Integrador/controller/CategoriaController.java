package com.Grupo3.Proyecto.Integrador.controller;

import com.Grupo3.Proyecto.Integrador.model.Categoria;
import com.Grupo3.Proyecto.Integrador.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<?> agregarCategoriaNueva(@RequestBody Categoria categoria){
        categoriaService.guardar(categoria);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public Collection<Categoria> listarTodasCategorias(){
        return categoriaService.buscarTodos();
    }

    @PutMapping
    public ResponseEntity<?> editarCategoria(@RequestBody Categoria categoria) {
        ResponseEntity<String> message = null;

        if (categoria.getId() == null) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria ID " + categoria.getId() + " no se encuentra, Intenta de nuevo.");
        } else {
            categoriaService.actualizar(categoria);
            message = ResponseEntity.ok("Categoria con ID " + categoria.getId() + " fue actualizada.");
        }

        return message;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminar(id);
        return ResponseEntity.ok("ID " + id + " fue eliminado.");
    }
}
