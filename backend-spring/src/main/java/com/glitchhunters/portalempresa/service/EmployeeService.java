package com.glitchhunters.portalempresa.service;

import com.glitchhunters.portalempresa.dto.EmployeeDto;
import com.glitchhunters.portalempresa.entity.Employee;
import com.glitchhunters.portalempresa.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() { return employeeRepository.findAll(); }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado con id " + id + " no encontrado."));
    }

    public Employee create(EmployeeDto dto) {
        return employeeRepository.save(Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .salary(dto.getSalary())
                .departmentId(dto.getDepartmentId())
                .build());
    }

    public Employee update(Long id, EmployeeDto dto) {
        // buscar el empleado y actualizar sus campos
        Employee emp = getById(id);
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setPhone(dto.getPhone());
        emp.setSalary(dto.getSalary());
        emp.setDepartmentId(dto.getDepartmentId());
        return employeeRepository.save(emp);
    }

    public void delete(Long id) { employeeRepository.deleteById(id); }
}
