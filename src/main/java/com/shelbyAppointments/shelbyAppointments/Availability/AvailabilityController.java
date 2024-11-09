package com.shelbyAppointments.shelbyAppointments.Availability;

import com.shelbyAppointments.shelbyAppointments.StoreClosure.StoreClosure;
import com.shelbyAppointments.shelbyAppointments.StoreClosure.StoreClosureDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@CrossOrigin
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/unavailable")
    public ResponseEntity<HairdresserUnavailability> setHairdresserUnavailable(@RequestBody UnavailabilityDTO request) {
        return ResponseEntity.ok(availabilityService.setHairdresserUnavailable(request));
    }

    @GetMapping("/{hairdresserId}/unavailable-dates")
    public ResponseEntity<List<HairdresserUnavailability>> getHairDresserUnavailableDates(@PathVariable String hairdresserId) {
        return ResponseEntity.ok(availabilityService.getUnavailableDates(hairdresserId));
    }

    @DeleteMapping("/unavailable/{id}")
    public ResponseEntity<Void> removeHairDresserUnavailableDate(@PathVariable String id) {
        availabilityService.removeUnavailableDate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/store-closure")
    public ResponseEntity<StoreClosure> setStoreClosed(@RequestBody StoreClosureDTO request) {
        return ResponseEntity.ok(availabilityService.setStoreClosed(request));
    }

    @GetMapping("/store-closures")
    public ResponseEntity<List<StoreClosure>> getStoreClosures() {
        return ResponseEntity.ok(availabilityService.getStoreClosures());
    }
}