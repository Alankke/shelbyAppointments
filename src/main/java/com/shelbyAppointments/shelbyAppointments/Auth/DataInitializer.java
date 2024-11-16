package com.shelbyAppointments.shelbyAppointments.Auth;

import com.shelbyAppointments.shelbyAppointments.User.User;
import com.shelbyAppointments.shelbyAppointments.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Only add if no users exist
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin@salon.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of("ROLE_ADMIN"));
            userRepository.save(admin);

            User stylist = new User();
            stylist.setUsername("stylist@salon.com");
            stylist.setPassword(passwordEncoder.encode("stylist123"));
            stylist.setRoles(Set.of("ROLE_STYLIST"));
            userRepository.save(stylist);
        }
    }
}