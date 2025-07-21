package com.edwin.forohub.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio.")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @NotBlank(message = "El status es obligatorio.")
    @Column(nullable = false)
    private String status;

    @NotBlank(message = "El autor es obligatorio.")
    @Column(nullable = false)
    private String autor;

    @NotBlank(message = "El curso es obligatorio.")
    @Column(nullable = false)
    private String curso;
}
