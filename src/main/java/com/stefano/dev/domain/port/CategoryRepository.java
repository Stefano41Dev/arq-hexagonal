package com.stefano.dev.domain.port;

import com.stefano.dev.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    // Todo - Nota: En los puertos (interfaces) no se usan Dto, se debe trabajar con objetos del dominio.
    // Representa las necesidades del negocio, no de la infraestructura.

    Category save(Category category);
    Optional<Category> findById(Integer id);
    Optional<Category> findByName(String name);
    List<Category> list();
    Category update(Category category);
    Void delete(Integer id);
}
