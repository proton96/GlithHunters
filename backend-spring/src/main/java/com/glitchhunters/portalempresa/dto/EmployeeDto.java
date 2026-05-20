package com.glitchhunters.portalempresa.dto;

import lombok.Data;
import java.math.BigDecimal;

// datos para crear o actualizar un empleado
@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private Long departmentId;
}
