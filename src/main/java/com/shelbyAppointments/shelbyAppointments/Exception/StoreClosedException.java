package com.shelbyAppointments.shelbyAppointments.Exception;

import java.time.LocalDate;

public class StoreClosedException extends RuntimeException {
    public StoreClosedException(LocalDate date) {
        super("The store is closed on " + date);
    }

    public StoreClosedException(LocalDate date, String startTime, String endTime) {
        super("The store is closed on " + date + " between " + startTime + " and " + endTime);
    }
}
