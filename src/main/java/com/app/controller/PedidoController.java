package com.app.controller;

import com.app.dto.PedidoDTO;
import com.app.entity.Pedido;
import com.app.service.PedidoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Validated
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id) {
        try {
            Pedido pedido = pedidoService.getPedidoById(id);
            return ResponseEntity.ok(pedido);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        pedidoService.savePedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePedido(@PathVariable Integer id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        try {
            pedidoService.updatePedido(id, pedidoDTO);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @GetMapping("/fecha/{startDate}/{endDate}")
    public ResponseEntity<List<Pedido>> getPedidosByFechaCreacion(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        List<Pedido> pedidos = pedidoService.getPedidosByFechaCreacion(startDate, endDate);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/precioMayorQue/{precioTotal}")
    public ResponseEntity<List<Pedido>> getPedidosByPrecioTotalGreaterThan(@PathVariable Double precioTotal) {
        List<Pedido> pedidos = pedidoService.getPedidosByPrecioTotalGreaterThan(precioTotal);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<Pedido>> getPedidosByProductoId(@PathVariable Integer idProducto) {
        List<Pedido> pedidos = pedidoService.getPedidosByProductoId(idProducto);
        return ResponseEntity.ok(pedidos);
    }
}
