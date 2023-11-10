package com.msvc.usuario.external.services;

import com.msvc.usuario.entities.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CALIFICACION-SERVICE")
public interface CalificacionService {

    @GetMapping("/calificaciones/usuarios/{usuarioId}")
    Calificacion[] obtenerCalificacionesPorUsuario(@PathVariable String usuarioId);

    @PostMapping("/calificaciones/")
    Calificacion guardarCalificacion(Calificacion calificacion);

    @PutMapping("/calificaciones/{calificacionId}")
    Calificacion actualizarCalificacion(@PathVariable String calificacionId, @RequestBody Calificacion calificacion);

    @DeleteMapping("/calificaciones/{calificacionId}")
    String eliminarCalificacion(@PathVariable String calificacionId);
}
