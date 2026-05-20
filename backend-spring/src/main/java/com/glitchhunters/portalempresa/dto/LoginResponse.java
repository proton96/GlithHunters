package com.glitchhunters.portalempresa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// respuesta al cliente
@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;    // JWT to attach as "Authorization: Bearer <token>"
    private String username;
    private String role;
}
