package com.shelbyAppointments.shelbyAppointments.Auth;

import com.shelbyAppointments.shelbyAppointments.Security.JwtUtil;
import com.shelbyAppointments.shelbyAppointments.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        log.info("Login attempt for email: {}", loginDTO.getEmail());

        try {
            // Try to load the user first to check if it exists
            UserDetails userDetails = userService.loadUserByUsername(loginDTO.getEmail());
            log.info("User found: {}", userDetails.getUsername());
            log.info("User authorities: {}", userDetails.getAuthorities());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            log.info("Authentication successful");

            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            log.info("JWT token generated");

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (UsernameNotFoundException e) {
            log.error("User not found: {}", loginDTO.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("User not found"));
        } catch (BadCredentialsException e) {
            log.error("Bad credentials for user: {}", loginDTO.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid credentials"));
        } catch (Exception e) {
            log.error("Unexpected error during authentication", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("An unexpected error occurred"));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("Test endpoint called");
        return ResponseEntity.ok("Auth controller is working!");
    }
}