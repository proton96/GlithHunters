package com.glitchhunters.portalempresa;

import com.glitchhunters.portalempresa.dto.StockMovementDto;
import com.glitchhunters.portalempresa.entity.Product;
import com.glitchhunters.portalempresa.entity.StockMovement;
import com.glitchhunters.portalempresa.repository.ProductoRepository;
import com.glitchhunters.portalempresa.repository.StockMovementRepository;
import com.glitchhunters.portalempresa.service.StockMovementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceTest {

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private StockMovementService stockMovementService;

    // producto con 10 unidades en stock
    private Product productoConStock(int stock) {
        return Product.builder().id(1L).name("Monitor").stock(stock).build();
    }

    private StockMovementDto dto(StockMovement.MovementType type, int quantity) {
        StockMovementDto d = new StockMovementDto();
        d.setProductId(1L);
        d.setType(type);
        d.setQuantity(quantity);
        return d;
    }

    @Test
    void getAll_devuelveTodosLosMovimientos() {
        StockMovement mov = StockMovement.builder().id(1L).build();
        when(stockMovementRepository.findAll()).thenReturn(List.of(mov));

        List<StockMovement> resultado = stockMovementService.getAll();

        assertEquals(1, resultado.size());
    }

    @Test
    void register_entradaDeStock_incrementaElStock() {
        Product producto = productoConStock(10);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any())).thenReturn(producto);
        when(stockMovementRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        stockMovementService.register(dto(StockMovement.MovementType.IN, 5));

        // el stock debe haber aumentado de 10 a 15
        assertEquals(15, producto.getStock());
        verify(productoRepository).save(producto);
    }

    @Test
    void register_salidaDeStock_decrementaElStock() {
        Product producto = productoConStock(10);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any())).thenReturn(producto);
        when(stockMovementRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        stockMovementService.register(dto(StockMovement.MovementType.OUT, 4));

        // el stock debe haber bajado de 10 a 6
        assertEquals(6, producto.getStock());
    }

    @Test
    void register_stockInsuficiente_lanzaExcepcion() {
        Product producto = productoConStock(3);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> stockMovementService.register(dto(StockMovement.MovementType.OUT, 10)));

        assertEquals("Stock insuficiente.", ex.getMessage());
        // el stock no debe haberse guardado
        verify(productoRepository, never()).save(any());
    }

    @Test
    void register_productoNoExiste_lanzaExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        StockMovementDto d = dto(StockMovement.MovementType.IN, 5);
        d.setProductId(99L);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> stockMovementService.register(d));

        assertTrue(ex.getMessage().contains("99"));
    }
}
