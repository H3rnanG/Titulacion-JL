package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer idProducto;
	
	@Column(name = "producto")
	private String producto;
	
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "compras_dia")
	private Integer comprasDia;
	
	@Column(name = "compras_semana")
	private Integer comprasSemana;
	
	@Column(name = "compras_mes")
	private Integer comprasMes;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

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

	public Integer getComprasDia() {
		return comprasDia;
	}

	public void setComprasDia(Integer comprasDia) {
		this.comprasDia = comprasDia;
	}

	public Integer getComprasSemana() {
		return comprasSemana;
	}

	public void setComprasSemana(Integer comprasSemana) {
		this.comprasSemana = comprasSemana;
	}

	public Integer getComprasMes() {
		return comprasMes;
	}

	public void setComprasMes(Integer comprasMes) {
		this.comprasMes = comprasMes;
	}

	public Producto(Integer idProducto, String producto, String descripcion, Double precio, String image,
			String categoria, Integer comprasDia, Integer comprasSemana, Integer comprasMes) {
		super();
		this.idProducto = idProducto;
		this.producto = producto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.image = image;
		this.categoria = categoria;
		this.comprasDia = comprasDia;
		this.comprasSemana = comprasSemana;
		this.comprasMes = comprasMes;
	}

	public Producto() {
		super();
	}
	
}
