package com.shelbyAppointments.shelbyAppointments.Availability;

import lombok.Data;

@Data
public class UnavailabilityDTO {
    private String hairdresserId;
    private String date;
    private String reason;
} 