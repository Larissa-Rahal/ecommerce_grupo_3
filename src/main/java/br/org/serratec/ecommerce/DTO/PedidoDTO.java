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
		BigDecimal vlr_total,
		@JsonIgnore
		Cliente cliente,
		List<ItemPedidoDTO> itemPedido,

		@JsonIgnore
		Long id_cliente
		) {

	public Pedido toEntity() {
		return Mapper.getMapper().convertValue(this, Pedido.class);
	}
	
	public static PedidoDTO toDto(Pedido pedidoEntity) {
		//return Mapper.getMapper().convertValue(pedidoEntity, PedidoDTO.class);
		return new PedidoDTO(pedidoEntity.getId(), pedidoEntity.getStatus(),
				pedidoEntity.getVlr_total(), pedidoEntity.getCliente(),
				pedidoEntity.getItem_pedido().stream()
						.map(p -> ItemPedidoDTO.toDto(p)).toList(),
				pedidoEntity.getCliente().getId());
	}




	
}
