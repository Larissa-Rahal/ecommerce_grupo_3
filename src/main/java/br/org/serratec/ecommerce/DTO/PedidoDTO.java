package br.org.serratec.ecommerce.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.ItemPedido;
import br.org.serratec.ecommerce.entity.Pedido;
import br.org.serratec.ecommerce.entity.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO (
		Long id,
		@NotNull
		Status status,
		@JsonIgnore
		BigDecimal vlr_total,
		@JsonIgnore
		Cliente cliente,
		List<ItemPedidoDTO> itemPedido,
		Long id_cliente,
		Long id_itemPedido,
		@JsonIgnore
		Long itemPedidoId
		) {

	public Pedido toEntity() {
		return Mapper.getMapper().convertValue(this, Pedido.class);
	}
	
	public static PedidoDTO toDto(Pedido pedidoEntity) {
		return Mapper.getMapper().convertValue(pedidoEntity, PedidoDTO.class);
	}




	
}
