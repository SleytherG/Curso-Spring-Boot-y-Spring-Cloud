package com.msvc.usuario.service.impl;

import com.msvc.usuario.entities.Calificacion;
import com.msvc.usuario.entities.Hotel;
import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.external.services.CalificacionService;
import com.msvc.usuario.external.services.HotelService;
import com.msvc.usuario.repository.UsuarioRepository;
import com.msvc.usuario.service.UsuarioService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CalificacionService calificacionService;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuarioId);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + usuarioId));

//        Calificacion[] calificacionesDelUsuario = restTemplate.getForObject("http://CALIFICACION-SERVICE/calificaciones/usuarios/" + usuario.getUsuarioId(), Calificacion[].class);
        Calificacion[] calificacionesDelUsuario = calificacionService.obtenerCalificacionesPorUsuario(usuario.getUsuarioId());
        List<Calificacion> calificaciones = Arrays.stream(calificacionesDelUsuario).collect(Collectors.toList());

        List<Calificacion> listaCalificaciones = calificaciones.stream()
                .map(calificacion -> {
                    System.out.println(calificacion.getHotelId());
//                    ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hoteles/" + calificacion.getHotelId(), Hotel.class);
                    Hotel hotel = hotelService.getHotel(calificacion.getHotelId());
//                    logger.info("Respuesta con codigo de estado: {}", forEntity.getStatusCode());

                    calificacion.setHotel(hotel);
                    return calificacion;
                }).collect(Collectors.toList());
        usuario.setCalificaciones(listaCalificaciones);

        return usuario;
    }

    @Override
    public Calificacion guardarCalificacion(Calificacion calificacionRequest) {
        return calificacionService.guardarCalificacion(calificacionRequest);
    }

    @Override
    public Calificacion actualizarCalificacion(String calificacionId, Calificacion calificacion) {
        return calificacionService.actualizarCalificacion(calificacionId, calificacion);
    }

    @Override
    public String eliminarCalificacion(String calificacionId) {
        return calificacionService.eliminarCalificacion(calificacionId);
    }
}
