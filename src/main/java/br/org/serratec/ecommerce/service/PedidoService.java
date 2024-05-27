package br.org.serratec.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.ecommerce.DTO.ClienteCadastroDTO;
import br.org.serratec.ecommerce.DTO.ItemPedidoDTO;
import br.org.serratec.ecommerce.DTO.PedidoDTO;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.ItemPedido;
import br.org.serratec.ecommerce.entity.Pedido;
import br.org.serratec.ecommerce.entity.Produto;
import br.org.serratec.ecommerce.repository.ClienteRepository;
import br.org.serratec.ecommerce.repository.ItemPedidoRepository;
import br.org.serratec.ecommerce.repository.PedidoRepository;
import br.org.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class PedidoService {
	@Autowired
	private ClienteService clienteservice;
	
	@Autowired
	private ProdutoRepository produtorepositorio;
	@Autowired
	private PedidoRepository pedidorepositorio;
	@Autowired
	private ItemPedidoRepository itempedidorepositorio;
	@Autowired
	private ClienteRepository clienterepositorio;
	@Autowired
	private EmailService emailService;
		
	public List<PedidoDTO> obterTodosPedidos(){		
		return pedidorepositorio.findAll().stream().map(p -> PedidoDTO.toDto(p)).toList();
	}
	
	public Optional<PedidoDTO> obterPedidoPorId(Long id) {
		Optional<Pedido> pedidoEntity = pedidorepositorio.findById(id);
		if (pedidoEntity.isEmpty()) {
			return Optional.of(PedidoDTO.toDto(pedidoEntity.get()));
		}
		return Optional.empty();
	}
	
	
	public PedidoDTO cadastrarPedido(@RequestBody PedidoDTO pedidoDTO) {
		Cliente cliente = clienterepositorio.findById(pedidoDTO.id_cliente()).get();
		Pedido pedidoEntity = pedidoDTO.toEntity();
        BigDecimal valorTotal = BigDecimal.ZERO;
        System.out.println(pedidoEntity.getCliente()+"-----------------------------");
        pedidoEntity.setCliente(cliente);

        pedidoEntity = pedidorepositorio.save(pedidoEntity);
		
		for(ItemPedidoDTO i : pedidoDTO.itemPedido()) {
			ItemPedido itemEntity = i.toEntity();
			Optional<Produto> produto = produtorepositorio.findById(i.produtoId());
			
			if(produto.isEmpty()) {
				throw new IllegalArgumentException("Id do produto é inválido");
			}
			
			BigDecimal valorVenda = produto.get().getVlr_unitario();
			BigDecimal valorBruto = valorVenda.multiply(new BigDecimal(itemEntity.getQnt()));
			BigDecimal valorDesconto = valorBruto.multiply(new BigDecimal(itemEntity.getPercentual()).divide(new BigDecimal(100))); 
			BigDecimal valorLiquido = valorBruto.subtract(valorDesconto);
			
	        itemEntity.setVlr_bruto(valorBruto);
	        itemEntity.setVlr_liquido(valorLiquido);
	        itemEntity.setPreco_venda(valorVenda);
	
	        valorTotal = valorTotal.add(valorLiquido);
			
	        pedidoEntity.setVlr_total(valorTotal);
	        itemEntity.setPedido(pedidoEntity);
			itemEntity.setProdutos(produto.get());
			itempedidorepositorio.save(itemEntity);
		}

		emailService.enviarEmailTexto(cliente.getEmail(), "Compra finalizada!",
                "Você está recebendo um email de confirmação de compra!");
        
        return PedidoDTO.toDto(pedidoEntity);
    }
	
	
	
	public Optional<PedidoDTO> atualizarPedido(Long id, PedidoDTO livro) {
		if (pedidorepositorio.existsById(id)) {
			Pedido pedidoEntity = livro.toEntity();
			pedidoEntity.setId(id);
			pedidorepositorio.save(pedidoEntity);
			return Optional.of(PedidoDTO.toDto(pedidoEntity));
		}
		return Optional.empty();
	}
	
	public boolean excluirPedido (Long id) {
		Optional<Pedido> pedido = pedidorepositorio.findById(id);
		if(pedido.isEmpty()) {
			return false;
		}
		pedidorepositorio.deleteById(id);
		return true;
	}

	 
	
}
