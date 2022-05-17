package com.Grupo3.Proyecto.Integrador.repository;

import com.Grupo3.Proyecto.Integrador.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
