package br.org.serratec.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import br.org.serratec.ecommerce.DTO.ClienteCadastroDTO;
import br.org.serratec.ecommerce.DTO.ClienteDTO;
import br.org.serratec.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteCadastroDTO cliente) {
		if (clienteService.cadastraCliente(cliente) == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(clienteService.cadastraCliente(cliente), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<Page<ClienteDTO>> obterTodosClientes(
			@PageableDefault(size=10, page=0, sort="nome", direction=Sort.Direction.ASC) Pageable pageable) {
		return new ResponseEntity<>(clienteService.obterTodosCliente(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ClienteDTO> obterClientePorId(@PathVariable Long id) {
		Optional<ClienteDTO> dto = clienteService.obterClientePorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
		Optional<ClienteDTO> dto = clienteService.atualizarCliente(id, cliente);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Obter todas as categorias",
	description = "Obter todas as categorias")
	public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
		if (!clienteService.excluirCliente(id)) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.noContent().build();
	}
}
