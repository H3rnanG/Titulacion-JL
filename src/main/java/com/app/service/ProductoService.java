package com.app.service;

import com.app.dto.ProductoDTO;
import com.app.entity.Producto;
import com.app.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public void saveProducto(ProductoDTO productoDTO) {
        Producto producto = mapper.map(productoDTO, Producto.class);
        productoRepository.saveAndFlush(producto);
    }

    public void updateProducto(Integer id, ProductoDTO productoDTO) {
        Producto productoFromDb = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        mapper.map(productoDTO, productoFromDb);
        productoRepository.saveAndFlush(productoFromDb);
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    // Personalizados
    
    public List<Producto> getProductosByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> getProductosByPrecioLessThan(Double precio) {
        return productoRepository.findByPrecioLessThan(precio);
    }

    public List<Producto> getProductosByNombre(String nombre) {
        return productoRepository.findByProductoContainingIgnoreCase(nombre);
    }
}
