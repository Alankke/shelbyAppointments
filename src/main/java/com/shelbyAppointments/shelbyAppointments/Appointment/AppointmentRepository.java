package com.shelbyAppointments.shelbyAppointments.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    boolean existsByDateAndTimeAndStylistAndStatusNot(
            LocalDate date,
            String time,
            String stylist,
            AppointmentStatus status
    );
}