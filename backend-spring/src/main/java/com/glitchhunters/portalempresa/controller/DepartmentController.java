package com.glitchhunters.portalempresa.controller;

import com.glitchhunters.portalempresa.entity.Department;
import com.glitchhunters.portalempresa.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD de departamentos — /api/departments
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAll() { return departmentService.getAll(); }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) { return departmentService.getById(id); }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
