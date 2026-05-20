import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-list.html',
  styleUrl: './employee-list.css'
})
export class EmployeeListComponent implements OnInit {
  private employeeService = inject(EmployeeService);

  employees: Employee[] = [];
  loading = true;
  error = '';

  ngOnInit(): void {
    this.employeeService.getAll().subscribe({
      next: (data) => {
        this.employees = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los empleados';
        this.loading = false;
      }
    });
  }

  delete(id: number | undefined): void {
    if (!id) return;
    this.employeeService.delete(id).subscribe(() => {
      // eliminar el empleado de la lista local tras borrar
      this.employees = this.employees.filter(e => e.id !== id);
    });
  }
}
