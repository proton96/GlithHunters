package com.glitchhunters.portalempresa.service;

import com.glitchhunters.portalempresa.dto.EmployeeDto;
import com.glitchhunters.portalempresa.entity.Department;
import com.glitchhunters.portalempresa.entity.Employee;
import com.glitchhunters.portalempresa.entity.User;
import com.glitchhunters.portalempresa.repository.DepartmentRepository;
import com.glitchhunters.portalempresa.repository.EmployeeRepository;
import com.glitchhunters.portalempresa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    public List<Employee> getAll() { return employeeRepository.findAll(); }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado con id: " + id + " no encontrado."));
    }

    public Employee create(EmployeeDto dto) {
        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado."));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return employeeRepository.save(Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .department(dept)
                .user(user)
                .checkedIn(false)
                .build());
    }

    public Employee update(Long id, EmployeeDto dto) {
        Employee emp = getById(id);
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        if (dto.getDepartmentId() != null) {
            emp.setDepartment(departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado.")));
        }
        return employeeRepository.save(emp);
    }

    public void delete(Long id) { employeeRepository.deleteById(id); }

    // marcar al empleado y registrar la hora
    public Employee checkIn(Long id) {
        Employee emp = getById(id);
        emp.setCheckedIn(true);
        emp.setLastCheckIn(LocalDateTime.now());
        return employeeRepository.save(emp);
    }

    public Employee checkOut(Long id) {
        Employee emp = getById(id);
        emp.setCheckedIn(false);
        emp.setLastCheckOut(LocalDateTime.now());
        return employeeRepository.save(emp);
    }

    // devolver empleados que están en la oficina
    public List<Employee> getCheckedIn() { return employeeRepository.findByCheckedInTrue(); }
}
