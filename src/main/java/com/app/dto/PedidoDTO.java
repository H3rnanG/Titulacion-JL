package com.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PedidoDTO {

	@NotNull(message = "La lista de productos es obligatoria")
    @Size(min = 1, message = "El pedido debe contener al menos un producto")
    private List<Integer> productoIds;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime fechaCreacion;

	public List<Integer> getProductoIds() {
		return productoIds;
	}

	public void setProductoIds(List<Integer> productoIds) {
		this.productoIds = productoIds;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
