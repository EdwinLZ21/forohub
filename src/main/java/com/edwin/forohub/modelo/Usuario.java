package com.edwin.forohub.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Column(nullable = false)
    private String password;
}
