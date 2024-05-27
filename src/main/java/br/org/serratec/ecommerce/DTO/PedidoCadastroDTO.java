package br.org.serratec.ecommerce.DTO;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Pedido;
import br.org.serratec.ecommerce.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoCadastroDTO(
		Long id,
		Status status,
		List<ItemPedidoDTO> itemPedido,
		Long id_cliente
		) {

	public Pedido toEntity() {
		return Mapper.getMapper().convertValue(this, Pedido.class);
	}
	
	public static PedidoCadastroDTO toDto(Pedido pedidoEntity) {
		return Mapper.getMapper().convertValue(pedidoEntity, PedidoCadastroDTO.class);
//		return new PedidoCadastroDTO(pedidoEntity.getId(), pedidoEntity.getStatus(),
//				pedidoEntity.getVlr_total(), pedidoEntity.getCliente(),
//				pedidoEntity.getItem_pedido().stream()
//						.map(p -> ItemPedidoDTO.toDto(p)).toList(),
//				pedidoEntity.getCliente().getId());
	}




	
}
