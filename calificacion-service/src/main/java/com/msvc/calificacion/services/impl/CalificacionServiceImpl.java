package com.msvc.calificacion.services.impl;

import com.msvc.calificacion.entity.Calificacion;
import com.msvc.calificacion.exceptions.ResourceNotFoundException;
import com.msvc.calificacion.repository.CalificacionRepository;
import com.msvc.calificacion.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion create(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public List<Calificacion> getCalificacionesByUsuarioId(String usuarioId) {
        return calificacionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Calificacion> getCalificacionesByHotelId(String hotelId) {
        return calificacionRepository.findByHotelId(hotelId);
    }

    @Override
    public Calificacion actualizarCalificacion(String calificacionId, Calificacion calificacionRequest) {
        Calificacion calificacion = calificacionRepository.findById(calificacionId).orElseThrow(() -> new ResourceNotFoundException("Calificacion no encontrada con el id: " + calificacionId));
        calificacion.setUsuarioId(calificacionRequest.getUsuarioId());
        calificacion.setHotelId(calificacionRequest.getHotelId());
        calificacion.setCalificacion(calificacionRequest.getCalificacion());
        calificacion.setObservaciones(calificacionRequest.getObservaciones());
        return calificacionRepository.save(calificacion);
    }

    @Override
    public void eliminarCalificacion(String calificacionId) {
         calificacionRepository.deleteById(calificacionId);
    }
}
