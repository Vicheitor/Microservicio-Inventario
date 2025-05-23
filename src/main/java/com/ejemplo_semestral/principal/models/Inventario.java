package com.ejemplo_semestral.principal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    private int inventarioid;
    private int productoid;
    private int stock;
}