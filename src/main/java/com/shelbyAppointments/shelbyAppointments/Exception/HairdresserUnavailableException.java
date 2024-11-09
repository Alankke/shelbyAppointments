package com.shelbyAppointments.shelbyAppointments.Exception;

import java.time.LocalDate;

public class HairdresserUnavailableException extends RuntimeException {
    public HairdresserUnavailableException(String hairdresserId, LocalDate date, String time) {
        super(String.format("El peluquero no está disponible el %s a las %s", date, time));
    }
}