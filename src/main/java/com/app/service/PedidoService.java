package com.app.service;

import com.app.dto.PedidoDTO;
import com.app.entity.Pedido;
import com.app.entity.Producto;
import com.app.repository.PedidoRepository;
import com.app.repository.ProductoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
    }

    public void savePedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());

        // Poblar la lista de productos
        List<Producto> productos = productoRepository.findByIdProductoIn(pedidoDTO.getProductoIds());
        if (productos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontraron productos con los IDs proporcionados");
        }
        pedido.setProductos(productos);

        // Calcular el precio total antes de guardar el pedido
        Double precioTotal = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        pedido.setPrecioTotal(precioTotal);

        pedidoRepository.saveAndFlush(pedido);
    }

    public void updatePedido(Integer id, PedidoDTO pedidoDTO) {
        Pedido pedidoFromDb = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        pedidoFromDb.setFechaCreacion(pedidoDTO.getFechaCreacion());

        // Poblar la lista de productos
        List<Producto> productos = productoRepository.findByIdProductoIn(pedidoDTO.getProductoIds());
        if (productos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontraron productos con los IDs proporcionados");
        }
        pedidoFromDb.setProductos(productos);

        // Recalcular el precio total al actualizar
        Double precioTotal = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        pedidoFromDb.setPrecioTotal(precioTotal);

        pedidoRepository.saveAndFlush(pedidoFromDb);
    }

    public void deletePedido(Integer id) {
        pedidoRepository.deleteById(id);
    }

    // Métodos personalizados
    public List<Pedido> getPedidosByFechaCreacion(LocalDateTime startDate, LocalDateTime endDate) {
        return pedidoRepository.findByFechaCreacionBetween(startDate, endDate);
    }

    public List<Pedido> getPedidosByPrecioTotalGreaterThan(Double precioTotal) {
        return pedidoRepository.findByPrecioTotalGreaterThan(precioTotal);
    }

    public List<Pedido> getPedidosByProductoId(Integer idProducto) {
        return pedidoRepository.findByProductos_IdProducto(idProducto);
    }
}
