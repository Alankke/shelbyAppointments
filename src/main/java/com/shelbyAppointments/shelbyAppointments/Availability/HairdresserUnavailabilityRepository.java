package com.shelbyAppointments.shelbyAppointments.Availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HairdresserUnavailabilityRepository extends JpaRepository<HairdresserUnavailability, String> {
    List<HairdresserUnavailability> findByHairdresserId(String hairdresserId);
    List<HairdresserUnavailability> findByDateAndHairdresserId(LocalDate date, String hairdresserId);
    boolean existsByDateAndHairdresserId(LocalDate date, String hairdresserId);
} 