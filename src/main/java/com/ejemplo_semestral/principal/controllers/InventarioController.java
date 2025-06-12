package com.ejemplo_semestral.principal.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo_semestral.principal.models.Inventario;
import com.ejemplo_semestral.principal.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/")
    public ResponseEntity<List<Inventario>> listarInventario() {
        return ResponseEntity.ok(inventarioService.listarInventario());
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarStock(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.agregarStock(inventario));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarStock(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.modificarStock(inventario));
    }

    @DeleteMapping("/eliminar/{productoid}")
    public ResponseEntity<String> eliminarStock(@PathVariable int productoid) {
        return ResponseEntity.ok(inventarioService.eliminarStock(productoid));
    }
}