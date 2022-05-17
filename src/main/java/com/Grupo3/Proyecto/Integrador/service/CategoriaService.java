package com.Grupo3.Proyecto.Integrador.service;

import com.Grupo3.Proyecto.Integrador.model.Categoria;
import com.Grupo3.Proyecto.Integrador.repository.ICategoriaRepository;
import com.Grupo3.Proyecto.Integrador.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements BaseService<Categoria> {

    ICategoriaRepository iCategoriaRepository;

    @Autowired
    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.iCategoriaRepository = categoriaRepository;
    }


    @Override
    public Categoria guardar(Categoria categoria) {
        return iCategoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodos() {
        return iCategoriaRepository.findAll();
    }

    @Override
    public Categoria buscar(Long id) {
        Categoria categoriaEncontrada = iCategoriaRepository.findById(id).orElse(null);
        return categoriaEncontrada;
    }

    @Override
    public void eliminar(Long id) {
        if (buscar(id) == null){
            System.out.println("No existe la categoria con id: " + id);
        }
        iCategoriaRepository.deleteById(id);
    }

    @Override
    public void actualizar(Categoria categoria) {
        if(buscar(categoria.getId()) == null){
            System.out.println("No existe la categoria con id: " + categoria.getId());
        }
        iCategoriaRepository.saveAndFlush(categoria);
    }
}
