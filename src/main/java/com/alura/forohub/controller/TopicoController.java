package com.alura.forohub.controller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.alura.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        // Regla de negocio: Verificar duplicados
        var duplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (duplicado) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        var topico = new Topico(datos);
        repository.save(topico);

        return ResponseEntity.ok("Tópico registrado exitosamente");
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 si el ID no existe
        }

        var datosTopico = new DatosListadoTopico(topicoOptional.get());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            // Regla de negocio: Verificar que no estemos creando un duplicado de otro tópico existente
            if (datos.titulo() != null && datos.mensaje() != null) {
                boolean existeDuplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
                if (existeDuplicado) {
                    return ResponseEntity.badRequest().body("Ya existe otro tópico con ese título y mensaje.");
                }
            }

            var topico = optionalTopico.get();
            topico.actualizarDatos(datos);
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.notFound().build(); // 404 si el ID no existe
    }


}