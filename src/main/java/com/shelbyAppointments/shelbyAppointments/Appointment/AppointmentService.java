package com.shelbyAppointments.shelbyAppointments.Appointment;

import com.shelbyAppointments.shelbyAppointments.Availability.AvailabilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AvailabilityService availabilityService;

    public AppointmentService(AppointmentRepository appointmentRepository, AvailabilityService availabilityService) {
        this.appointmentRepository = appointmentRepository;
        this.availabilityService = availabilityService;
    }

    public Appointment createAppointment(AppointmentDTO request) {
        availabilityService.validateAvailability(request.getDate(), request.getTime(), request.getStylist());

        Appointment appointment = new Appointment();
        appointment.setClientName(request.getClientName());
        appointment.setEmail(request.getEmail());
        appointment.setPhone(request.getPhone());
        appointment.setService(request.getService());
        appointment.setStylist(request.getStylist());  // Add this line
        appointment.setDate(LocalDate.parse(request.getDate()));
        appointment.setTime(request.getTime());
        appointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment updateStatus(String id, StatusUpdateDTO request) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));

        appointment.setStatus(request.getStatus());
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        appointmentRepository.delete(appointment);
    }
}
