package com.shelbyAppointments.shelbyAppointments.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class AuthResponse {
    private String token;
}