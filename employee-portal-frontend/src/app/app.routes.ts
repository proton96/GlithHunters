import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/pages/login/login').then(m => m.LoginComponent)
  },
  {
    path: '',
    loadComponent: () =>
      import('./shared/components/layout/layout').then(m => m.LayoutComponent),
    children: [
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./features/dashboard/pages/dashboard/dashboard').then(m => m.DashboardComponent)
      },
      {
        path: 'employees',
        loadComponent: () =>
          import('./features/employees/pages/employee-list/employee-list').then(m => m.EmployeeListComponent)
      },
      {
        path: 'departments',
        loadComponent: () =>
          import('./features/departments/pages/department-list/department-list').then(m => m.DepartmentListComponent)
      },
      {
        path: 'inventory',
        loadComponent: () =>
          import('./features/inventory/pages/product-list/product-list').then(m => m.ProductListComponent)
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '**',
    redirectTo: ''
  }
];
