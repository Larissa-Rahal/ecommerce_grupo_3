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

import br.org.serratec.ecommerce.DTO.ItemPedidoCadastroDTO;
import br.org.serratec.ecommerce.DTO.ItemPedidoDTO;
import br.org.serratec.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/carrinho")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itempedidoService;
	
	@PostMapping
	public ResponseEntity<ItemPedidoDTO> cadastrarItemPedido(@RequestBody ItemPedidoCadastroDTO itemPedido) {
		return new ResponseEntity<>(itempedidoService.cadastrarItemPedido(itemPedido), HttpStatus.CREATED);
	}
	
	@PostMapping("-id")
	public ResponseEntity<ItemPedidoDTO> cadastrarItemPedidoPorId(@RequestBody ItemPedidoCadastroDTO itemPedido) {
		return new ResponseEntity<>(itempedidoService.gravarItemPedidoPorId(itemPedido), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ItemPedidoDTO>> obterTodosItemPedido() {
		return new ResponseEntity<>(itempedidoService.obterTodosItemPedido(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoDTO> obterItemPedidoPorId(@PathVariable Long id) {
		Optional<ItemPedidoDTO> dto = itempedidoService.obterItemPedidoPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemPedidoDTO> atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDTO itemPedido) {
		Optional<ItemPedidoDTO> dto = itempedidoService.atualizarItemPedido(id, itemPedido);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerItemPedido(@PathVariable Long id) {
		if (!itempedidoService.excluirItemPedido(id)) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.noContent().build();
	}
}
