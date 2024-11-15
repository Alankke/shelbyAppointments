package com.shelbyAppointments.shelbyAppointments.Availability;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hairdresser_unavailability")
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class HairdresserUnavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String hairdresserId;
    private LocalDate date;
    private boolean fullDay;
    private String startTime;
    private String endTime;
    private String reason;

    @CreationTimestamp
    private LocalDateTime createdAt;
}