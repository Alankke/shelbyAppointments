package com.shelbyAppointments.shelbyAppointments.Appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String clientName;
    private String email;
    private String phone;
    private String service;
    private String stylist;
    private LocalDate date;
    private String time;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private AppointmentStatus status = AppointmentStatus.PENDING;
}