package com.shelbyAppointments.shelbyAppointments.Appointment;

import lombok.Data;

@Data
public class AppointmentDTO {
    private String clientName;
    private String email;
    private String phone;
    private String service;
    private String stylist;
    private String date;
    private String time;
    private String status;
}