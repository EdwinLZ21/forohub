package com.edwin.forohub.controlador;

import com.edwin.forohub.modelo.Topico;
import com.edwin.forohub.repositorio.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Crear tópico
    @PostMapping
    public ResponseEntity<String> crearTopico(@Valid @RequestBody Topico topico) {
        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un tópico con ese título y mensaje.");
        }
        topico.setFechaCreacion(LocalDateTime.now());
        topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tópico creado exitosamente.");
    }

    // Listar todos los tópicos
    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    // Ver detalle de un tópico por id
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTopico(@PathVariable Long id, @Valid @RequestBody Topico topicoActualizado) {
        Optional<Topico> topicoDB = topicoRepository.findById(id);
        if (topicoDB.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Topico t = topicoDB.get();
        t.setTitulo(topicoActualizado.getTitulo());
        t.setMensaje(topicoActualizado.getMensaje());
        t.setStatus(topicoActualizado.getStatus());
        t.setAutor(topicoActualizado.getAutor());
        t.setCurso(topicoActualizado.getCurso());
        topicoRepository.save(t);
        return ResponseEntity.ok("Tópico actualizado exitosamente.");
    }

    // Eliminar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.ok("Tópico eliminado correctamente.");
    }
}
