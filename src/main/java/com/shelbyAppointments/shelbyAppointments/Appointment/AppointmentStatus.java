package com.shelbyAppointments.shelbyAppointments.Appointment;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AppointmentStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}