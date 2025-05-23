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
            inventarioRepository.deleteByProductoid(productoid);
            return "Stock eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar stock: " + e.getMessage();
        }
    }

    public String modificarStock(Inventario inventario) {
        try {
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