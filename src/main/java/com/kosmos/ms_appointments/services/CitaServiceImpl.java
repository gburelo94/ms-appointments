package com.kosmos.ms_appointments.services;

import com.kosmos.ms_appointments.exceptions.CustomException;
import com.kosmos.ms_appointments.entities.Cita;
import com.kosmos.ms_appointments.repositories.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.kosmos.ms_appointments.configs.Mensajes.*;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService{

    private CitaRepository citaRepository;

    @Override
    public Cita agendar(Cita cita) {
        validarCita(cita);
        return citaRepository.save(cita);
    }

    @Override
    public Cita cancelar(Long citaId) {

        Cita existe = citaRepository
                .findById(citaId)
                .orElseThrow(() -> new CustomException(CITA_NO_ENCONTRADA));

        if (existe.getFecha().isBefore(LocalDateTime.now()))
            throw new CustomException(NO_PUEDE_CANCELAR_CITA_PASADA);

        existe.setCancelada(true);

        return citaRepository.save(existe);
    }

    @Override
    public Cita actualizar(Long citaId, Cita cita) {

        Cita existe = citaRepository
                .findById(citaId)
                .orElseThrow(() -> new CustomException(CITA_NO_ENCONTRADA));

        cita.setId(citaId);

        validarCita(cita);

        return citaRepository.save(cita);
    }

    @Override
    public void validarCita(Cita cita) {

        LocalDateTime hora = cita.getFecha();

        LocalDateTime ini = hora.toLocalDate().atStartOfDay();

        LocalDateTime fin = hora.toLocalDate().atTime(23,59);

        if(!citaRepository.findByConsultorioIdAndFecha(hora, cita.getConsultorio().getId()).isEmpty())
            throw new CustomException(CONSULTORIO_OCUPADO);


        if (!citaRepository.findByDoctorIdAndFechaBetween(cita.getDoctor().getId(), hora, hora.plusMinutes(1)).isEmpty())
            throw new CustomException(DOCTOR_OCUPADO);

        List<Cita> citas = citaRepository.findByPacienteAndFechaBetween(cita.getPaciente(), ini, fin);

        for (Cita c : citas) {

            Duration d = Duration.between(c.getFecha(), hora).abs();

            if (d.toHours() < 2) throw new CustomException(PACIENTE_CON_CITA_MENOS_2_HORAS);
        }

        long count = citaRepository.countByDoctorIdAndFechaBetween(cita.getDoctor().getId(), ini, fin);

        if (count >= 8)
            throw new CustomException(DOCTOR_LLEGO_LIMITE);

    }

    @Override
    public List<Cita> consultarCitas(LocalDate fecha, Long doctorId, Long consultorioId) {

        LocalDateTime ini = fecha.atStartOfDay();

        LocalDateTime fin = fecha.atTime(23, 59);

        if (doctorId != null)
            return citaRepository.findByDoctorIdAndFechaBetween(doctorId, ini, fin);

        if (consultorioId != null)
            return citaRepository.findByConsultorioIdAndFecha(ini, consultorioId);

        return citaRepository.findAll();
    }
}
