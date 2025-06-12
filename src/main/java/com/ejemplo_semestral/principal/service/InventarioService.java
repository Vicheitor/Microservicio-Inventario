package com.ejemplo_semestral.principal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            // Validaciones
            if (inventario.getProductoid() <= 0) {
                return "Error: El ID del producto debe ser mayor que cero.";
            }
            if (inventario.getStock() < 0) {
                return "Error: El stock no puede ser negativo.";
            }
            if (inventarioRepository.existsByProductoid(inventario.getProductoid())) {
                return "Error: Ya existe un producto con el ID " + inventario.getProductoid();
            }

            InventarioEntity entity = convertToEntity(inventario);
            inventarioRepository.save(entity);
            return "Stock agregado correctamente";
        } catch (Exception e) {
            return "Error al agregar stock: " + e.getMessage();
        }
    }

    @Transactional
    public String eliminarStock(int productoid) {
        try {
            if (!inventarioRepository.existsByProductoid(productoid)) {
                return "Error: No se encontró el producto con ID " + productoid;
            }
            inventarioRepository.deleteByProductoid(productoid);
            return "Stock eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar stock: " + e.getMessage();
        }
    }

    public String modificarStock(Inventario inventario) {
        try {
            // Validaciones
            if (inventario.getProductoid() <= 0) {
                return "Error: El ID del producto debe ser mayor que cero.";
            }
            if (inventario.getStock() < 0) {
                return "Error: El stock no puede ser negativo.";
            }
            if (!inventarioRepository.existsByProductoid(inventario.getProductoid())) {
                return "Error: No se puede modificar porque el producto con ID " + inventario.getProductoid() + " no existe.";
            }

            InventarioEntity entity = convertToEntity(inventario);
            inventarioRepository.save(entity);
            return "Stock modificado correctamente";
        } catch (Exception e) {
            return "Error al modificar stock: " + e.getMessage();
        }
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
