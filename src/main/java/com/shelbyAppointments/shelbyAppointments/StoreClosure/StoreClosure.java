package com.shelbyAppointments.shelbyAppointments.StoreClosure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class StoreClosure {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDate date;
    private boolean fullDay;
    private String startTime;
    private String endTime;
    private String reason;

    @CreationTimestamp
    private LocalDateTime createdAt;
}