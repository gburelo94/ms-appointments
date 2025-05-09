package com.kosmos.ms_appointments.controllers;

import com.kosmos.ms_appointments.entities.Cita;
import com.kosmos.ms_appointments.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.agendar(cita));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Cita> consultar(@RequestParam(required = false) LocalDate fecha,
                                @RequestParam(required = false) Long doctorId,
                                @RequestParam(required = false) Long consultorioId) {
        if (fecha == null) fecha = LocalDate.now();
        return citaService.consultarCitas(fecha, doctorId, consultorioId);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(citaService.cancelar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.actualizar(id, cita));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
