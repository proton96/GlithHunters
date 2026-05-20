package com.glitchhunters.portalempresa.controller;

import com.glitchhunters.portalempresa.dto.EmployeeDto;
import com.glitchhunters.portalempresa.entity.Employee;
import com.glitchhunters.portalempresa.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD de empleados — /api/employees
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() { return employeeService.getAll(); }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) { return employeeService.getById(id); }

    @PostMapping
    public Employee create(@RequestBody EmployeeDto dto) { return employeeService.create(dto); }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        return employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
