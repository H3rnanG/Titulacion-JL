package com.app.controller;

import com.app.dto.ProductoDTO;
import com.app.entity.Producto;
import com.app.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        try {
            Producto producto = productoService.getProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        productoService.saveProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProducto(@PathVariable Integer id, @Valid @RequestBody ProductoDTO productoDTO) {
        try {
            productoService.updateProducto(id, productoDTO);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.getProductosByCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/precioMenorQue/{precio}")
    public ResponseEntity<List<Producto>> getProductosByPrecioLessThan(@PathVariable Double precio) {
        List<Producto> productos = productoService.getProductosByPrecioLessThan(precio);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Producto>> getProductosByNombre(@PathVariable String nombre) {
        List<Producto> productos = productoService.getProductosByNombre(nombre);
        return ResponseEntity.ok(productos);
    }
}
