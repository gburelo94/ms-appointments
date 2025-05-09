package com.kosmos.ms_appointments.repositories;

import com.kosmos.ms_appointments.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByDoctorIdAndFechaBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Cita> findByConsultorioIdAndFecha(LocalDateTime horario, Long consultorioId);

    List<Cita> findByPacienteAndFechaBetween(String paciente, LocalDateTime start, LocalDateTime end);

    long countByDoctorIdAndFechaBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

}
