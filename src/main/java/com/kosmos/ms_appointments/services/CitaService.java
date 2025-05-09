package com.kosmos.ms_appointments.services;


import com.kosmos.ms_appointments.entities.Cita;

import java.time.LocalDate;
import java.util.List;

public interface CitaService {

    Cita agendar(Cita cita);

    Cita cancelar(Long citaId);

    Cita actualizar(Long citaId, Cita cita);

    void validarCita(Cita cita);

    List<Cita> consultarCitas(LocalDate fecha, Long doctorId, Long consultorioId);
}
