import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../models/department.model';

@Component({
  selector: 'app-department-list',
  imports: [CommonModule],
  templateUrl: './department-list.html',
  styleUrl: './department-list.css'
})
export class DepartmentListComponent implements OnInit {
  private departmentService = inject(DepartmentService);

  departments: Department[] = [];
  loading = true;
  error = '';

  ngOnInit(): void {
    this.departmentService.getAll().subscribe({
      next: (data) => {
        this.departments = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los departamentos';
        this.loading = false;
      }
    });
  }
}
