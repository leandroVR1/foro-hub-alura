package com.alura.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Método para buscar duplicados
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}