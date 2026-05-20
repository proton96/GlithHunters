package com.glitchhunters.portalempresa.service;

import com.glitchhunters.portalempresa.entity.Department;
import com.glitchhunters.portalempresa.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() { return departmentRepository.findAll(); }

    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento con id: " + id + " no encontrado."));
    }

    public Department create(String name) {
        return departmentRepository.save(Department.builder().name(name).build());
    }

    public Department update(Long id, String name) {
        Department dept = getById(id);
        dept.setName(name);
        return departmentRepository.save(dept);
    }

    public void delete(Long id) { departmentRepository.deleteById(id); }
}
