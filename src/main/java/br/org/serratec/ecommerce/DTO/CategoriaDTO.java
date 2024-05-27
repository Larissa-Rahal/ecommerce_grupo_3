package br.org.serratec.ecommerce.DTO;

import java.util.List;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Categoria;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Produto;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO( 
	    Long id,
		@NotBlank(message = "O nome não pode ser nulo.")
	    String nome,
	    @NotBlank(message = "A descrição não pode ser nula.")
	    String descricao

	    ) {

	    public Categoria toEntity() {
	        return Mapper.getMapper().convertValue(this, Categoria.class);
	    }
	    
	    public static CategoriaDTO toDto(Categoria categoriaEntity) {
			return Mapper.getMapper().convertValue(categoriaEntity, CategoriaDTO.class);
		}

	

	} 