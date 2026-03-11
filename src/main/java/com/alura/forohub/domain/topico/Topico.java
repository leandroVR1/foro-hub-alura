package com.alura.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String status;
    private String autor;
    private String curso;


    private Boolean activo;

    public Topico(DatosRegistroTopico datos) {
        this.activo = true; // Todo tópico nuevo nace activo
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.status = "NO_RESPONDIDO";
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    // Método para cambiar el estado a inactivo
    public void eliminarLogico() {
        this.activo = false;
    }
}