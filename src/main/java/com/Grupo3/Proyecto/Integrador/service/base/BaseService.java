package com.Grupo3.Proyecto.Integrador.service.base;

import java.util.List;

public interface BaseService <T> {

    T guardar (T t);
    List<T> buscarTodos();
    T buscar(Long id);
    void eliminar(Long id);
    void actualizar(T t);

}
