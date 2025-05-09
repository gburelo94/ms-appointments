package com.kosmos.ms_appointments.repositories;

import com.kosmos.ms_appointments.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
