package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.dto.PedidoDTO;
import com.app.dto.ProductoDTO;
import com.app.entity.Pedido;
import com.app.entity.Producto;

@SpringBootApplication
public class ProyectoJlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoJlApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Configurar mapeos personalizados si es necesario
        modelMapper.typeMap(ProductoDTO.class, Producto.class).addMappings(mapper -> {
            mapper.skip(Producto::setIdProducto);
        });
        
        modelMapper.typeMap(PedidoDTO.class, Pedido.class).addMappings(mapper -> {
            mapper.skip(Pedido::setIdPedido);
            mapper.skip(Pedido::setPrecioTotal); // Este se calculará en el servicio
        });

        return modelMapper;
    }

}
