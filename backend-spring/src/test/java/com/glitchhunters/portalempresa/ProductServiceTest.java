package com.glitchhunters.portalempresa;

import com.glitchhunters.portalempresa.dto.ProductDto;
import com.glitchhunters.portalempresa.entity.Product;
import com.glitchhunters.portalempresa.repository.ProductoRepository;
import com.glitchhunters.portalempresa.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductService productService;

    // producto de prueba reutilizable
    private Product productoEjemplo() {
        return Product.builder()
                .id(1L)
                .name("Teclado")
                .sku("TEC-001")
                .stock(10)
                .minStock(2)
                .price(new BigDecimal("49.99"))
                .build();
    }

    @Test
    void getAll_devuelveTodosLosProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(productoEjemplo()));

        List<Product> resultado = productService.getAll();

        assertEquals(1, resultado.size());
        assertEquals("Teclado", resultado.get(0).getName());
    }

    @Test
    void getById_encontrado_devuelveProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEjemplo()));

        Product resultado = productService.getById(1L);

        assertEquals("TEC-001", resultado.getSku());
    }

    @Test
    void getById_noEncontrado_lanzaExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productService.getById(99L));

        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    void create_guardaElProductoCorrectamente() {
        ProductDto dto = new ProductDto();
        dto.setName("Raton");
        dto.setSku("RAT-001");
        dto.setStock(5);
        dto.setMinStock(1);
        dto.setPrice(new BigDecimal("19.99"));

        Product guardado = Product.builder().id(2L).name("Raton").build();
        when(productoRepository.save(any(Product.class))).thenReturn(guardado);

        Product resultado = productService.create(dto);

        assertEquals("Raton", resultado.getName());
        verify(productoRepository).save(any(Product.class));
    }

    @Test
    void update_actualizaLosCamposDelProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEjemplo()));
        when(productoRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        ProductDto dto = new ProductDto();
        dto.setName("Teclado Mecanico");
        dto.setSku("TEC-002");
        dto.setStock(8);
        dto.setMinStock(2);
        dto.setPrice(new BigDecimal("89.99"));

        Product resultado = productService.update(1L, dto);

        assertEquals("Teclado Mecanico", resultado.getName());
        assertEquals(8, resultado.getStock());
    }

    @Test
    void getLowStock_devuelveProductosConStockBajo() {
        Product conPocoStock = Product.builder().id(2L).name("Cable").stock(1).build();
        when(productoRepository.findByStockLessThan(5)).thenReturn(List.of(conPocoStock));

        List<Product> resultado = productService.getLowStock(5);

        assertEquals(1, resultado.size());
        assertEquals("Cable", resultado.get(0).getName());
    }
}
