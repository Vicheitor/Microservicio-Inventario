package com.ejemplo_semestral.principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ejemplo_semestral.principal.models.Inventario;
import com.ejemplo_semestral.principal.repository.InventarioRepository;
import com.ejemplo_semestral.principal.service.InventarioService;

@ExtendWith(MockitoExtension.class)
public class PrincipalApplicationTests {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void testAgregarStockExitoso() {
        when(inventarioRepository.existsByProductoid(anyInt())).thenReturn(false);
        
        Inventario inventario = new Inventario(0, 101, 10);
        String resultado = inventarioService.agregarStock(inventario);
        
        assertEquals("Stock agregado correctamente", resultado);
        verify(inventarioRepository).save(any());
    }

    @Test
    void testAgregarStockConIdExistente() {
        when(inventarioRepository.existsByProductoid(anyInt())).thenReturn(true);
        
        Inventario inventario = new Inventario(0, 101, 10);
        
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, 
            () -> inventarioService.agregarStock(inventario));
        
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Ya existe un producto con el ID"));
    }

    @Test
    void testAgregarStockNegativo() {
        Inventario inventario = new Inventario(0, 101, -5);
        
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, 
            () -> inventarioService.agregarStock(inventario));
        
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("El stock no puede ser negativo.", exception.getReason());
    }

    @Test
    void testEliminarStockExitoso() {
        when(inventarioRepository.existsByProductoid(anyInt())).thenReturn(true);
        doNothing().when(inventarioRepository).deleteByProductoid(anyInt());
        
        String resultado = inventarioService.eliminarStock(101);
        
        assertEquals("Stock eliminado correctamente", resultado);
        verify(inventarioRepository).deleteByProductoid(101);
    }

    @Test
    void testEliminarStockNoExistente() {
        when(inventarioRepository.existsByProductoid(anyInt())).thenReturn(false);
        
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, 
            () -> inventarioService.eliminarStock(999));
        
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("No se encontró el producto con ID"));
    }

    @Test
    void testModificarStockExitoso() {
        when(inventarioRepository.existsByProductoid(anyInt())).thenReturn(true);
        
        Inventario inventario = new Inventario(1, 101, 15);
        String resultado = inventarioService.modificarStock(inventario);
        
        assertEquals("Stock modificado correctamente", resultado);
        verify(inventarioRepository).save(any());
    }
}