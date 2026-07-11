package com.stefano.dev.domain.port;

import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageRequest;
import com.stefano.dev.domain.pagination.PageResult;


import java.util.Optional;


public interface GameRepository {
    // Nota: En los puertos (interfaces) no se usan Dto, se debe trabajar con objetos del dominio.
    // Representa las necesidades del negocio, no de la infraestructura.

    Game save(Game game);
    Optional<Game> findById(Integer id);
    PageResult<Game> findByName(String name, PageRequest pageRequest);
    PageResult<Game> list(PageRequest pageRequest);



}
