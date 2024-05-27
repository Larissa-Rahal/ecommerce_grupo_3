package br.org.serratec.ecommerce.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoCadastroDTO(
		@NotBlank(message = "A descrição não pode ser nula.")
		String descricao,
		@NotNull(message = "A quantidade de estoque não pode ser nula.")
		Integer qtd_estoque,
		@NotBlank(message = "A data de cadastro não pode ser nula.")
		LocalDate dt_cadastro,
		@NotNull(message = "O valor unitário não pode ser nulo.")
		BigDecimal vlr_unitario,
		Long categoriaId
		) {
	
	public Produto toEntity() {
		return Mapper.getMapper().convertValue(this, Produto.class);
	}
}
