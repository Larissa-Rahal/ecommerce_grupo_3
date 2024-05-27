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

import br.org.serratec.ecommerce.DTO.PedidoDTO;
import br.org.serratec.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	@Operation(summary = "Cadastro de pedido",
	description = "Serve para cadastrar um pedido")
	public ResponseEntity<PedidoDTO> cadastrarPedido(@RequestBody PedidoDTO pedido) {
		return new ResponseEntity<>(pedidoService.cadastrarPedido(pedido), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	@Operation(summary = "Obter todos os pedidos",
	description = "Serve para obter todos os pedidos")
	public ResponseEntity<List<PedidoDTO>> obterTodosPedidos() {
		return new ResponseEntity<>(pedidoService.obterTodosPedidos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obter pedido por Id",
	description = "Serve para obter um pedido ja cadastrado por Id")
	public ResponseEntity<PedidoDTO> obterPedidoPorId(@PathVariable Long id) {
		Optional<PedidoDTO> dto = pedidoService.obterPedidoPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar pedido",
	description = "Serve para atualizar um pedido ja existente")
	public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedido) {
		Optional<PedidoDTO> dto = pedidoService.atualizarPedido(id, pedido);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar pedido",
	description = "Serve para deletar um pedido ja existente")
	public ResponseEntity<Void> removerPedido(@PathVariable Long id) {
		if (!pedidoService.excluirPedido(id)) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.noContent().build();
	}
}
