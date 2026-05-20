package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByCheckedInTrue();  // empleados en la oficina
}
