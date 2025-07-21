package com.edwin.forohub.controlador;

import com.edwin.forohub.modelo.LoginRequest;
import com.edwin.forohub.seguridad.JwtService;
import com.edwin.forohub.repositorio.UsuarioRepository;
import com.edwin.forohub.modelo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<Usuario> userOpt = usuarioRepository.findByUsername(req.getUsername());
        if (userOpt.isEmpty() || !passwordEncoder.matches(req.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
        String token = jwtService.generateToken(req.getUsername());
        return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
    }
}
