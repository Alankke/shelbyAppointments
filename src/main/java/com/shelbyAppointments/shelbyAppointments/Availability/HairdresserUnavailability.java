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
public class HairdresserUnavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String hairdresserId;
    private LocalDate date;
    private String reason;

    @CreationTimestamp
    private LocalDateTime createdAt;
}