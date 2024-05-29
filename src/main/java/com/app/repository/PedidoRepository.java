package com.app.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    // Método personalizado para encontrar pedidos por fecha de creación
    List<Pedido> findByFechaCreacionBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Método personalizado para encontrar pedidos con un precio total mayor a un cierto valor
    List<Pedido> findByPrecioTotalGreaterThan(Double precioTotal);

    // Método personalizado para encontrar pedidos que contienen un producto específico
    List<Pedido> findByProductos_IdProducto(Integer idProducto);
    
}