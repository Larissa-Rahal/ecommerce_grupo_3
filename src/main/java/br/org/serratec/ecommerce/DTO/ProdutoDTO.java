package br.org.serratec.ecommerce.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Categoria;
import br.org.serratec.ecommerce.entity.Produto;
import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO(
	Long id,
	@NotBlank(message = "A descrição não pode ser nula.")
	String descricao,
	@NotBlank(message = "A imagem não pode ser inválida.")
	String imagem,
	@NotBlank(message = "A quantidade de estoque não pode ser nula.")
	Integer qtd_estoque,
	@NotBlank(message = "A data de cadastro não pode ser nula.")
	LocalDate dt_cadastro,
	@NotBlank(message = "O valor unitário não pode ser nulo.")
	BigDecimal vlr_unitario,
	Categoria categoria
	) {
	
	public Produto toEntity() {
		return Mapper.getMapper().convertValue(this, Produto.class);
	}

	public static ProdutoDTO toDto(Produto produtoEntity) {
		return Mapper.getMapper().convertValue(produtoEntity, ProdutoDTO.class);
	
	}

	

}