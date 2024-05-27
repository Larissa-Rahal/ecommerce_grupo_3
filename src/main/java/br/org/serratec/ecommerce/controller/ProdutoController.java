package br.org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.ecommerce.DTO.ProdutoCadastroDTO;
import br.org.serratec.ecommerce.DTO.ProdutoDTO;
import br.org.serratec.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;	
	
	@PostMapping
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoCadastroDTO produto) {
		return new ResponseEntity<>(produtoService.cadastrarProduto(produto), HttpStatus.CREATED);
	}
	
	@PostMapping("/id")
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ProdutoDTO> cadastrarProdutoPorId(@RequestBody ProdutoCadastroDTO produto) {
		return new ResponseEntity<>(produtoService.gravarProdutoPorId(produto), HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<List<ProdutoDTO>> obterTodosProdutos() {
		return new ResponseEntity<>(produtoService.obterTodosProdutos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}") 
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ProdutoDTO> obterProdutoPorId(@PathVariable Long id) {
		Optional<ProdutoDTO> dto = produtoService.obterProdutoPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
		Optional<ProdutoDTO> dto = produtoService.atualizarProduto(id, produto);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
		if (!produtoService.excluirProduto(id)) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.noContent().build();
	}
}
