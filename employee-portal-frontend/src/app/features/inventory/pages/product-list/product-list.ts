tsimport { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryService } from '../../services/inventory.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-list',
  imports: [CommonModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductListComponent implements OnInit {
  private inventoryService = inject(InventoryService);

  products: Product[] = [];
  loading = true;
  error = '';

  ngOnInit(): void {
    this.inventoryService.getAll().subscribe({
      next: (data) => {
        this.products = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'No se pudieron cargar los productos';
        this.loading = false;
      }
    });
  }
}