package br.org.serratec.ecommerce.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long id;
	private Integer qnt; //quantidade;
	private BigDecimal preco_venda;
	private Integer percentual; //percentual desconto
	private BigDecimal vlr_liquido; //valor_liquido
	private BigDecimal vlr_bruto; // valor_bruto
//	private List<Produto> produtos;
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_produto")
	private Produto produtos;	
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	@JsonIgnore
	private Pedido pedido;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQnt() {
		return qnt;
	}

	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}

	public BigDecimal getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(BigDecimal preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Integer getPercentual() {
		return percentual;
	}

	public void setPercentual(Integer percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getVlr_liquido() {
		return vlr_liquido;
	}

	public void setVlr_liquido(BigDecimal vlr_liquido) {
		this.vlr_liquido = vlr_liquido;
	}

	public BigDecimal getVlr_bruto() {
		return vlr_bruto;
	}

	public void setVlr_bruto(BigDecimal vlr_bruto) {
		this.vlr_bruto = vlr_bruto;
	}

	

//	public List<Produto> getProduto() {
//		return produto;
//	}
//
//	public void setProduto(List<Produto> produto) {
//		this.produto = produto;
//	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProdutos() {
		return produtos;
	}

	public void setProdutos(Produto produtos) {
		this.produtos = produtos;
	}

	

	

	

	
	
	
	
}
