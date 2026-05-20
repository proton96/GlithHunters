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
                .orElseThrow(() -> new RuntimeException("Departamento con id " + id + " no encontrado."));
    }

    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    public Department update(Long id, Department data) {
        Department dept = getById(id);
        dept.setName(data.getName());
        dept.setDescription(data.getDescription());
        return departmentRepository.save(dept);
    }

    public void delete(Long id) { departmentRepository.deleteById(id); }
}
