package com.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoDTO {

	@NotBlank(message = "El nombre del producto es Obligatorio")
    @Size(min = 1, max = 100)
    private String producto;

    @Size(max = 500)
    private String descripcion;

    @NotNull(message = "El precio es Obligatorio")
    @Min(0)
    private Double precio;

    @NotBlank(message = "La imagen es Obligatoria")
    private String image;

    @NotBlank(message = "La categoria es Obligatoria")
    @Size(min = 1, max = 50)
    private String categoria;

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

    
}
