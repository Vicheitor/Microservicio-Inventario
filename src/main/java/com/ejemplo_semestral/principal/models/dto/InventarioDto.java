package com.ejemplo_semestral.principal.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDto {
    private int inventarioid;
    private int productoid;
    private int stock;
    
}
