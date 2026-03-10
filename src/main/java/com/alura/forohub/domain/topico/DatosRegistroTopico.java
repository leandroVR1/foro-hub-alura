package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotBlank(message = "El autor es obligatorio")
        String autor,

        @NotBlank(message = "Debes especificar el curso")
        String curso
) {
}