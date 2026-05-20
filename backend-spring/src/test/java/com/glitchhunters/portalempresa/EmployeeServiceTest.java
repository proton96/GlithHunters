package com.glitchhunters.portalempresa;

import com.glitchhunters.portalempresa.dto.EmployeeDto;
import com.glitchhunters.portalempresa.entity.Employee;
import com.glitchhunters.portalempresa.repository.EmployeeRepository;
import com.glitchhunters.portalempresa.service.EmployeeService;
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
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    // empleado de prueba reutilizable
    private Employee empleadoEjemplo() {
        return Employee.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Garcia")
                .email("juan@empresa.com")
                .build();
    }

    @Test
    void getAll_devuelveTodosLosEmpleados() {
        when(employeeRepository.findAll()).thenReturn(List.of(empleadoEjemplo()));

        List<Employee> resultado = employeeService.getAll();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getFirstName());
    }

    @Test
    void getById_encontrado_devuelveEmpleado() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(empleadoEjemplo()));

        Employee resultado = employeeService.getById(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getFirstName());
    }

    @Test
    void getById_noEncontrado_lanzaExcepcion() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> employeeService.getById(99L));

        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    void create_guardaElEmpleadoCorrectamente() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName("Maria");
        dto.setLastName("Lopez");
        dto.setEmail("maria@empresa.com");

        Employee guardado = Employee.builder().id(2L).firstName("Maria").lastName("Lopez").build();
        when(employeeRepository.save(any(Employee.class))).thenReturn(guardado);

        Employee resultado = employeeService.create(dto);

        assertEquals("Maria", resultado.getFirstName());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void update_actualizaLosCamposDelEmpleado() {
        Employee existente = empleadoEjemplo();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(i -> i.getArgument(0));

        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName("Pedro");
        dto.setLastName("Ruiz");
        dto.setEmail("pedro@empresa.com");

        Employee resultado = employeeService.update(1L, dto);

        assertEquals("Pedro", resultado.getFirstName());
        assertEquals("Ruiz", resultado.getLastName());
    }

    @Test
    void delete_llamaAlRepositorioConElIdCorrecto() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.delete(1L);

        verify(employeeRepository).deleteById(1L);
    }
}
