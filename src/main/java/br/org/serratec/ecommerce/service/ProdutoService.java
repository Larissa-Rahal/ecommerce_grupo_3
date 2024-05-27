package br.org.serratec.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.DTO.ProdutoCadastroDTO;
import br.org.serratec.ecommerce.DTO.ProdutoDTO;
import br.org.serratec.ecommerce.entity.Categoria;
import br.org.serratec.ecommerce.entity.Produto;
import br.org.serratec.ecommerce.repository.CategoriaRepository;
import br.org.serratec.ecommerce.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {
	
	@Autowired
	private CategoriaRepository categoriarepositorio;
	
	@Autowired
	private ProdutoRepository produtorepositorio;
	
	public List<ProdutoDTO> obterTodosProdutos(){
		return produtorepositorio.findAll().stream().map(p -> ProdutoDTO.toDto(p))
				.toList();
	}
	
	public Optional<ProdutoDTO> obterProdutoPorId(Long id) {
		Optional<Produto> produtoEntity = produtorepositorio.findById(id);
		if (produtoEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(ProdutoDTO.toDto(produtoEntity.get())); 
	}
	
	public ProdutoDTO cadastrarProduto(ProdutoCadastroDTO produto) {
		Produto produtoEntity = produtorepositorio.save(produto.toEntity());
		return ProdutoDTO.toDto(produtoEntity);
	}
	
	public ProdutoDTO gravarProdutoPorId(ProdutoCadastroDTO produto) {
		Produto produtoEntity = produto.toEntity();
		try {
			Optional<Categoria> categoriaPorId = categoriarepositorio.findById(produto.categoriaId());
			produtoEntity.setCategoria(categoriaPorId.get());
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException("Id da categoria é inválido.");
        }

        produtorepositorio.save(produtoEntity);
        return ProdutoDTO.toDto(produtoEntity);
		
	}
	
	public Optional<ProdutoDTO> atualizarProduto(Long id, @Valid ProdutoDTO produto) {
		if (produtorepositorio.existsById(id)) {
			Produto produtoEntity = produto.toEntity();
			produtoEntity.setId(id);
			produtorepositorio.save(produtoEntity);
			return Optional.of(ProdutoDTO.toDto(produtoEntity));
		}
		return Optional.empty();
	}
	
	public boolean excluirProduto(Long id) {
		Optional<Produto> produto = produtorepositorio.findById(id);
			if(produto.isEmpty()){
			return false;
		}
		
		produtorepositorio.deleteById(id);
		return true;
	}

		
	
}
