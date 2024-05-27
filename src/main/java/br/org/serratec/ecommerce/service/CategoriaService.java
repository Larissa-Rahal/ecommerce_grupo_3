package br.org.serratec.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.DTO.CategoriaDTO;
import br.org.serratec.ecommerce.entity.Categoria;
import br.org.serratec.ecommerce.entity.Pedido;
import br.org.serratec.ecommerce.entity.Produto;
import br.org.serratec.ecommerce.repository.CategoriaRepository;
import br.org.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriarepositorio;
	
	public List<CategoriaDTO> obterTodasCategorias(){		
		return categoriarepositorio.findAll().stream().map(c -> CategoriaDTO.toDto(c))
				.toList();
	}
	
	public Optional<CategoriaDTO> obterCategoriaPorId(Long id) {
		Optional<Categoria> categoriaEntity = categoriarepositorio.findById(id);
		if (categoriaEntity.isEmpty()) {
			return Optional.of(CategoriaDTO.toDto(categoriaEntity.get()));
		}
		return Optional.empty();
	}
	
	public CategoriaDTO cadastraCategoria(CategoriaDTO categoria) {
		Categoria categoriaEntity = categoriarepositorio.save(categoria.toEntity());
		return CategoriaDTO.toDto(categoriaEntity);
	}
	
	public Optional<CategoriaDTO> atualizarCategoria(Long id, CategoriaDTO categoria) {
		if (categoriarepositorio.existsById(id)) {
			Categoria categoriaEntity = categoria.toEntity();
			categoriaEntity.setId(id);
			categoriarepositorio.save(categoriaEntity);
			return Optional.of(CategoriaDTO.toDto(categoriaEntity));
		}
		return Optional.empty();
	}
	
	public boolean excluirCategoria (Long id) {
		Optional<Categoria> categoria = categoriarepositorio.findById(id);
		if(categoria.isEmpty()) {
			return false;
		}
		categoriarepositorio.deleteById(id);
		return true;
	}
}
