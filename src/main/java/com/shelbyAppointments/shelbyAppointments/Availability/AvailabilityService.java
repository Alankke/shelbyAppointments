package com.shelbyAppointments.shelbyAppointments.Availability;

import com.shelbyAppointments.shelbyAppointments.Appointment.AppointmentRepository;
import com.shelbyAppointments.shelbyAppointments.Appointment.AppointmentStatus;
import com.shelbyAppointments.shelbyAppointments.Exception.HairdresserUnavailableException;
import com.shelbyAppointments.shelbyAppointments.Exception.StoreClosedException;
import com.shelbyAppointments.shelbyAppointments.StoreClosure.StoreClosure;
import com.shelbyAppointments.shelbyAppointments.StoreClosure.StoreClosureDTO;
import com.shelbyAppointments.shelbyAppointments.StoreClosure.StoreClosureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AvailabilityService {
    private final HairdresserUnavailabilityRepository unavailabilityRepository;
    private final StoreClosureRepository storeClosureRepository;
    private final AppointmentRepository appointmentRepository;

    public AvailabilityService(HairdresserUnavailabilityRepository repository, StoreClosureRepository storeClosureRepository, AppointmentRepository appointmentRepository) {
        this.unavailabilityRepository = repository;
        this.storeClosureRepository = storeClosureRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public HairdresserUnavailability setHairdresserUnavailable(UnavailabilityDTO request) {
        HairdresserUnavailability unavailability = new HairdresserUnavailability();

        unavailability.setHairdresserId(request.getHairdresserId());
        unavailability.setDate(LocalDate.parse(request.getDate()));
        unavailability.setReason(request.getReason());

        return unavailabilityRepository.save(unavailability);
    }

    public List<HairdresserUnavailability> getUnavailableDates(String hairdresserId) {
        return unavailabilityRepository.findByHairdresserId(hairdresserId);
    }

    public void removeUnavailableDate(String id) {
        if (!unavailabilityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unavailable date not found");
        }
        unavailabilityRepository.deleteById(id);
    }

    public StoreClosure setStoreClosed(StoreClosureDTO request) {
        StoreClosure storeClosure = new StoreClosure();
        storeClosure.setDate(LocalDate.parse(request.getDate()));
        storeClosure.setFullDay(request.isFullDay());

        if (!request.isFullDay()) {
            validateTimeRange(request.getStartTime(), request.getEndTime());
            storeClosure.setStartTime(request.getStartTime());
            storeClosure.setEndTime(request.getEndTime());
        }

        storeClosure.setReason(request.getReason());
        return storeClosureRepository.save(storeClosure);
    }

    public List<StoreClosure> getStoreClosures() {
        return storeClosureRepository.findByDateGreaterThanEqual(LocalDate.now());
    }

    public void validateAvailability(String dateStr, String time, String hairdresserId) {
        LocalDate date = LocalDate.parse(dateStr);
        validateStoreAvailability(date, time);
        validateHairdresserDayOff(date, hairdresserId);
        validateHairdresserAppointments(date, time, hairdresserId);
    }

    private void validateStoreAvailability(LocalDate date, String time) {
        List<StoreClosure> closures = storeClosureRepository.findByDate(date);

        for (StoreClosure closure : closures) {
            if (closure.isFullDay()) {
                throw new StoreClosedException(date);
            }

            // Check if the requested time falls within a partial closure period
            if (!closure.isFullDay() && time != null) {
                LocalTime appointmentTime = LocalTime.parse(time);
                LocalTime closureStart = LocalTime.parse(closure.getStartTime());
                LocalTime closureEnd = LocalTime.parse(closure.getEndTime());

                if (!appointmentTime.isBefore(closureStart) && !appointmentTime.isAfter(closureEnd)) {
                    throw new StoreClosedException(date, closure.getStartTime(), closure.getEndTime());
                }
            }
        }
    }

    private void validateHairdresserDayOff(LocalDate date, String hairdresserId) {
        boolean isHairdresserUnavailable = unavailabilityRepository
                .existsByDateAndHairdresserId(date, hairdresserId);
        if (isHairdresserUnavailable) {
            throw new HairdresserUnavailableException(hairdresserId, date, null);
        }
    }

    private void validateHairdresserAppointments(LocalDate date, String time, String hairdresserId) {
        boolean hasAppointment = appointmentRepository
                .existsByDateAndTimeAndStylistAndStatusNot(
                        date,
                        time,
                        hairdresserId,
                        AppointmentStatus.CANCELLED
                );
        if (hasAppointment) {
            throw new HairdresserUnavailableException(hairdresserId, date, time);
        }
    }

    private void validateTimeRange(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Start time and end time are required for partial day closures");
        }

        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        if (!start.isBefore(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Start time must be before end time");
        }
    }
}