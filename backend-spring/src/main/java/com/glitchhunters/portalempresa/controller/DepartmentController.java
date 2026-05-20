package com.glitchhunters.portalempresa.controller;

import com.glitchhunters.portalempresa.entity.Department;
import com.glitchhunters.portalempresa.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Department create(@RequestBody Map<String, String> body) {
        return departmentService.create(body.get("name"));
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return departmentService.update(id, body.get("name"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
