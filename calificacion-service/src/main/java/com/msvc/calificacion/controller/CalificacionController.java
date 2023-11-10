package com.msvc.calificacion.controller;

import com.msvc.calificacion.entity.Calificacion;
import com.msvc.calificacion.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @PostMapping("/")
    public ResponseEntity<Calificacion> guardarCalificacion(@RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionService.create(calificacion));
    }

    @GetMapping("/")
    public ResponseEntity<List<Calificacion>> listarCalificaciones() {
        return ResponseEntity.ok().body(calificacionService.getCalificaciones());
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Calificacion>> listarCalificacionesPorUsuarioId(@PathVariable String usuarioId) {
        return ResponseEntity.ok().body(calificacionService.getCalificacionesByUsuarioId(usuarioId));
    }

    @GetMapping("/hoteles/{hotelId}")
    public ResponseEntity<List<Calificacion>> listarCalificacionesPorHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok().body(calificacionService.getCalificacionesByHotelId(hotelId));
    }

    @PutMapping("/{calificacionId}")
    public ResponseEntity<Calificacion> actualizarCalificacion(@PathVariable String calificacionId, @RequestBody Calificacion calificacion) {
        return ResponseEntity.ok().body(calificacionService.actualizarCalificacion(calificacionId, calificacion));
    }

    @DeleteMapping("/{calificacionId}")
    public ResponseEntity<String> eliminarCalificacion(@PathVariable String calificacionId) throws Exception {
        try {
            calificacionService.eliminarCalificacion(calificacionId);
            return ResponseEntity.status(HttpStatus.OK).body("Calificación eliminada exitosamente");
        } catch ( Exception e) {
            throw new Exception(e);
        }
    }
}
