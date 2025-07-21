package com.edwin.forohub.controlador;

import com.edwin.forohub.modelo.Usuario;
import com.edwin.forohub.servicio.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        usuarioService.registrarUsuario(usuario.getUsername(), usuario.getPassword());
        return ResponseEntity.ok("Usuario registrado correctamente.");
    }
}
