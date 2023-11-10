package com.msvc.usuario.service;

import com.msvc.usuario.entities.Calificacion;
import com.msvc.usuario.entities.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    List<Usuario> getAllUsuarios();

    Usuario getUsuario(String usuarioId);

    Calificacion guardarCalificacion(Calificacion calificacion);

    Calificacion actualizarCalificacion(String calificacionId);

    String eliminarCalificacion(String calificacionId);

}
