package com.ejemplo_semestral.principal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ejemplo_semestral.principal.models.Inventario;
import com.ejemplo_semestral.principal.models.entity.InventarioEntity;
import com.ejemplo_semestral.principal.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll().stream()
            .map(this::convertToModel)
            .toList();
    }

    public String agregarStock(Inventario inventario) {
        if (inventario.getProductoid() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del producto debe ser mayor que cero.");
        }
        if (inventario.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El stock no puede ser negativo.");
        }
        if (inventarioRepository.existsByProductoid(inventario.getProductoid())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un producto con el ID " + inventario.getProductoid());
        }

        InventarioEntity entity = convertToEntity(inventario);
        inventarioRepository.save(entity);
        return "Stock agregado correctamente";
    }

    @Transactional
    public String eliminarStock(int productoid) {
        if (!inventarioRepository.existsByProductoid(productoid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el producto con ID " + productoid);
        }
        inventarioRepository.deleteByProductoid(productoid);
        return "Stock eliminado correctamente";
    }

    public String modificarStock(Inventario inventario) {
        if (inventario.getProductoid() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del producto debe ser mayor que cero.");
        }
        if (inventario.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El stock no puede ser negativo.");
        }
        if (!inventarioRepository.existsByProductoid(inventario.getProductoid())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede modificar porque el producto con ID " + inventario.getProductoid() + " no existe.");
        }

        InventarioEntity entity = convertToEntity(inventario);
        inventarioRepository.save(entity);
        return "Stock modificado correctamente";
    }

    private Inventario convertToModel(InventarioEntity entity) {
        return new Inventario(
            entity.getInventarioid(),
            entity.getProductoid(),
            entity.getStock()
        );
    }

    private InventarioEntity convertToEntity(Inventario model) {
        InventarioEntity entity = new InventarioEntity();
        entity.setInventarioid(model.getInventarioid());
        entity.setProductoid(model.getProductoid());
        entity.setStock(model.getStock());
        return entity;
    }
}
