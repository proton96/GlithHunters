package com.glitchhunters.portalempresa.dto;

import lombok.Data;

// enviado por el cliente en POST
@Data
public class LoginRequest {
    private String username;
    private String password;
}
