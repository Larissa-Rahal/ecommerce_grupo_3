package br.org.serratec.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.ItemPedido;
import br.org.serratec.ecommerce.entity.Pedido;
import br.org.serratec.ecommerce.entity.Produto;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoDTO (
		 Long id,
		 @NotNull(message = "A quantidade não pode ser nula.")
		 Integer qnt, 
		 @NotNull(message = "O preço de venda não pode ser nulo.")
		 BigDecimal preco_venda,
		 @NotNull(message = "O percentual não pode ser nulo.")
		 Integer percentual, 
		 @NotNull(message = "O valor líquido não pode ser nulo.")
		 @JsonIgnore
		 BigDecimal vlr_liquido, 
		 @NotNull(message = "O valor bruto não pode ser nulo.")
		 @JsonIgnore
		 BigDecimal vlr_bruto,
		 @JsonIgnore
		 Pedido pedido,

		 //@NotNull(message = "O id do pedido não pode ser nulo.")
		 Long pedidoId,
		
		 //@NotNull(message = "O id do produto não pode ser nulo.")
		 Long produtoId,

		 PedidoProdutoDto produtos
		 
		){
	
	public ItemPedido toEntity() {
		return Mapper.getMapper().convertValue(this, ItemPedido.class);
	}
	
	public static ItemPedidoDTO toDto(ItemPedido itempedidoEntity) {
		return Mapper.getMapper().convertValue(itempedidoEntity, ItemPedidoDTO.class);
	}

}
