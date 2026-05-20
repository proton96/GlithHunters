package com.glitchhunters.portalempresa.dto;

import lombok.Data;

// actualización del empleado
@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
    private Long userId;
}
