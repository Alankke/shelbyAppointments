package com.shelbyAppointments.shelbyAppointments.Availability;

import lombok.Data;

@Data
public class UnavailabilityDTO {
    private String hairdresserId;
    private String date;
    private boolean fullDay;
    private String startTime;
    private String endTime;
    private String reason;
}