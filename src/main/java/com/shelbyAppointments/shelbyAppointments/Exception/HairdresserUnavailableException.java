package com.shelbyAppointments.shelbyAppointments.Exception;

import java.time.LocalDate;

public class HairdresserUnavailableException extends RuntimeException {
    public HairdresserUnavailableException(String hairdresserId, LocalDate date, String time) {
        super(String.format(
                "Hairdresser %s is not available on %s%s",
                hairdresserId,
                date,
                time != null ? " during " + time : ""
        ));
    }
}