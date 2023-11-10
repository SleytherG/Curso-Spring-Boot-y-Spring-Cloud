package com.msvc.usuario.controllers;

import com.msvc.usuario.entities.Calificacion;
import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuarioRequest) {
        Usuario usuario = usuarioService.saveUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String usuarioId) {
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/calificaciones/")
    public ResponseEntity<Calificacion> guardarCalificacion(@RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.guardarCalificacion(calificacion));
    }

    @PutMapping("/calificaciones/{calificacionId}")
    public ResponseEntity<Calificacion> actualizarCalificacion(@PathVariable String calificacionId, @RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.actualizarCalificacion(calificacionId, calificacion));
    }

    @DeleteMapping("/calificaciones/{calificacionId}")
    public ResponseEntity<String> eliminarCalificacion(@PathVariable String calificacionId) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.eliminarCalificacion(calificacionId));
    }

}
