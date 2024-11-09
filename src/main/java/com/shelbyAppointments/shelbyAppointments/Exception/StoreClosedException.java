package com.shelbyAppointments.shelbyAppointments.Exception;

import java.time.LocalDate;

public class StoreClosedException extends RuntimeException {
    public StoreClosedException(LocalDate date) {
        super("No se pueden crear turnos en días que el local está cerrado: " + date);
    }
}
