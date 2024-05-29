package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	List<Producto> findByIdProductoIn(List<Integer> ids);

    // Método personalizado para encontrar productos por categoría
    List<Producto> findByCategoria(String categoria);

    // Método personalizado para encontrar productos cuyo precio esté por debajo de un cierto valor
    List<Producto> findByPrecioLessThan(Double precio);

    // Método personalizado para encontrar productos por nombre
    List<Producto> findByProductoContainingIgnoreCase(String nombre);
    
}