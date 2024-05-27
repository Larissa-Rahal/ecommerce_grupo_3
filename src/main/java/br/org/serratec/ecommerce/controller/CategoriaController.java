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

import br.org.serratec.ecommerce.DTO.CategoriaDTO;
import br.org.serratec.ecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
	@Operation(summary = "Cadastrar categoria",
	description = "Serve para cadastrar uma categoria")
	public ResponseEntity<CategoriaDTO> cadastrarCategoria(@RequestBody CategoriaDTO categoria) {
		return new ResponseEntity<>(categoriaService.cadastraCategoria(categoria), HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<List<CategoriaDTO>> obterTodosCategorias() {
		return new ResponseEntity<>(categoriaService.obterTodasCategorias(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obter categoria por Id",
	description = "Obter categoria por Id")
	public ResponseEntity<CategoriaDTO> obterCategoriaPorId(@PathVariable Long id) {
		Optional<CategoriaDTO> dto = categoriaService.obterCategoriaPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar categoria",
	description = "Atualizar categoria ja cadastrada")
	public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
		Optional<CategoriaDTO> dto = categoriaService.atualizarCategoria(id, categoria);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Remover categoria",
	description = "Esse item remove a categoria")
	public ResponseEntity<Void> removerCategoria(@PathVariable Long id) {
		if (!categoriaService.excluirCategoria(id)) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.noContent().build();
	}
}
