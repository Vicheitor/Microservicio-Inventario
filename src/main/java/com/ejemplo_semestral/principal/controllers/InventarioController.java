package com.ejemplo_semestral.principal.controllers;

import com.ejemplo_semestral.principal.models.Inventario;
import com.ejemplo_semestral.principal.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        return ResponseEntity.ok(inventarioService.listarInventario());
    }

    @PostMapping
    public ResponseEntity<String> agregarStock(@RequestBody Inventario inventario) {
        String respuesta = inventarioService.agregarStock(inventario);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    public ResponseEntity<String> modificarStock(@RequestBody Inventario inventario) {
        String respuesta = inventarioService.modificarStock(inventario);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{productoid}")
    public ResponseEntity<String> eliminarStock(@PathVariable int productoid) {
        String respuesta = inventarioService.eliminarStock(productoid);
        return ResponseEntity.ok(respuesta);
    }
}
