package com.ejemplo_semestral.principal.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventario")
@Data
public class InventarioEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "inventario_id")
    private int inventarioid;

    @Column(name = "producto_id")
    private int productoid;

    @Column(name = "stock")
    private int stock;
}