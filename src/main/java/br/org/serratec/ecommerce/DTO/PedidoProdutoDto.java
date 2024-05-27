package br.org.serratec.ecommerce.DTO;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Categoria;
import br.org.serratec.ecommerce.entity.Produto;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoProdutoDto(Long id,
                               String descricao,
                               @NotBlank(message = "O valor unitário não pode ser nulo.")
                               BigDecimal vlr_unitario,
                               Categoria categoria
) {

    public Produto toEntity() {
        return Mapper.getMapper().convertValue(this, Produto.class);
    }

    public static PedidoProdutoDto toDto(Produto produtoEntity) {
        return Mapper.getMapper().convertValue(produtoEntity, PedidoProdutoDto.class);

    }
}
