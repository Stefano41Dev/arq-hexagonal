package com.stefano.dev.domain.port;

import com.stefano.dev.domain.model.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface GameRepository {
    // Nota: En los puertos (interfaces) no se usan Dto, se debe trabajar con objetos del dominio.
    // Representa las necesidades del negocio, no de la infraestructura.

    Game save(Game game);
    Optional<Game> findById(Integer id);
    Optional<Game> findByName(String name);
    List<Game> list();
    Game update(Game game);
    Void delete(Integer id);

}
