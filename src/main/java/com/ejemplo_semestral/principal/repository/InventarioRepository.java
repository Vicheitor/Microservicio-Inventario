package com.ejemplo_semestral.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ejemplo_semestral.principal.models.entity.InventarioEntity;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {
    List<InventarioEntity> findByProductoid(int productoid);
    boolean existsByProductoid(int productoid);
    void deleteByProductoid(int productoid);
}
