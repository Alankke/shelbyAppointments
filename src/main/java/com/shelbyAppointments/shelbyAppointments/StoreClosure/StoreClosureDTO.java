package com.shelbyAppointments.shelbyAppointments.StoreClosure;

import lombok.Data;

@Data
public class StoreClosureDTO {
    private String date;
    private boolean fullDay;
    private String startTime;
    private String endTime;
    private String reason;
}