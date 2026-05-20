package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// hereda los métodos básicos: findAll, findById, save, deleteById
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
