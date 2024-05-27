package br.org.serratec.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.DTO.ItemPedidoCadastroDTO;
import br.org.serratec.ecommerce.DTO.ItemPedidoDTO;
import br.org.serratec.ecommerce.entity.ItemPedido;
import br.org.serratec.ecommerce.entity.Pedido;
import br.org.serratec.ecommerce.entity.Produto;
import br.org.serratec.ecommerce.repository.ItemPedidoRepository;
import br.org.serratec.ecommerce.repository.PedidoRepository;
import br.org.serratec.ecommerce.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itempedidorepositorio;
	@Autowired
	private PedidoRepository pedidorepositorio;
	@Autowired
	private ProdutoRepository produtorepositorio;
	
	public List<ItemPedidoDTO> obterTodosItemPedido() {
		return itempedidorepositorio.findAll().stream().map(i -> ItemPedidoDTO.toDto(i))
				.toList();
	}
	
	public Optional<ItemPedidoDTO> obterItemPedidoPorId(Long id) {
		Optional<ItemPedido> itempedidoEntity = itempedidorepositorio.findById(id);
		if (itempedidoEntity.isEmpty()) {
			return Optional.of(ItemPedidoDTO.toDto(itempedidoEntity.get()));
		}
		return Optional.empty();
	}
	
	public ItemPedidoDTO cadastrarItemPedido(ItemPedidoCadastroDTO itempedido) {
		ItemPedido itempedidoEntity = itempedidorepositorio.save(itempedido.toEntity());
		return ItemPedidoDTO.toDto(itempedidoEntity);
	}
	
	public ItemPedidoDTO gravarItemPedidoPorId(ItemPedidoCadastroDTO itemPedido) {
		ItemPedido itempedidoEntity = itemPedido.toEntity();
		try {
			Optional<Pedido> pedido = pedidorepositorio.findById(itemPedido.pedidoId());
			itempedidoEntity.setPedido(pedido.get());
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException("Id do pedido é inválido");
		}
		
		try {
			Optional<Produto> produto = produtorepositorio.findById(itemPedido.produtoId());
			itempedidoEntity.setProdutos(produto.get());
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException("Id do produto é inválido");
		}
		
//		List<Produto> produtos = new ArrayList<>();
//		
//		try {
//			for (Long produtoId : itemPedido.produtosId()) {
//				Optional<Produto> produto = produtorepositorio.findById(produtoId);
//				produtos.add(produto.get());
//			}
//			itempedidoEntity.setProduto(produtos);
//		} catch (NoSuchElementException ex) {
//			throw new IllegalArgumentException("Id do produto é inválido");
//		}
		
		itempedidorepositorio.save(itempedidoEntity);
		return ItemPedidoDTO.toDto(itempedidoEntity);
	}
	
	public Optional<ItemPedidoDTO> atualizarItemPedido(Long id, @Valid ItemPedidoDTO itempedido) {
		if (itempedidorepositorio.existsById(id)) {
			ItemPedido itempedidoEntity = itempedido.toEntity();
			itempedidoEntity.setId(id);
			itempedidorepositorio.save(itempedidoEntity);
			return Optional.of(ItemPedidoDTO.toDto(itempedidoEntity));
		}
		return Optional.empty();
	}
	
	
	public boolean excluirItemPedido(Long id) {
		Optional<ItemPedido> itempedido = itempedidorepositorio.findById(id);
		if (itempedido.isEmpty()) {
			return false;
		}
		
		itempedidorepositorio.deleteById(id);
		return true;
	}
	
	
}
