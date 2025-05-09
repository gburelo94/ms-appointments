package com.kosmos.ms_appointments.configs;

public class Mensajes {

    public static final String CONSULTORIO_OCUPADO = "El consultorio seleccionado ya está ocupado a esa hora.";

    public static final String DOCTOR_OCUPADO = "El doctor seleccionado ya cuenta con una cita agendada a esa hora.";

    public static final String PACIENTE_CON_CITA_MENOS_2_HORAS = "Este paciente ya tiene una cita agendada con menos de 2 horas de diferencia.";

    public static final String DOCTOR_LLEGO_LIMITE = "El doctor seleccionado ya tiene 8 citas agendadas para este día.";

    public static final String NO_PUEDE_CANCELAR_CITA_PASADA = "No se puede cancelar una cita pasada.";

    public static final String CITA_NO_ENCONTRADA = "La cita seleccionada no existe.";

    public Mensajes() {
        throw new IllegalStateException("");
    }
}
