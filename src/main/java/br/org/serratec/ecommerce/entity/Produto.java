package br.org.serratec.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_produto")
	    private Long id;
	    private String descricao;
	    private Integer qtd_estoque; //quantidade_estoque
	    private LocalDate dt_cadastro; //data_cadastro
	    private BigDecimal vlr_unitario; //valor_unitario
	    private String imagem;
	    
	    
	    @ManyToOne(cascade = CascadeType.ALL)
	    private Categoria categoria;
	    
	
	    @OneToMany(mappedBy = "produtos")
	    @JsonIgnore
	    private List<ItemPedido> item_pedido;
	    
	    public Produto() {
	    	
	    }
	    
		public Produto(Long id, String descricao, Integer qtd_estoque, LocalDate dt_cadastro, BigDecimal vlr_unitario,
				String imagem, Categoria categoria) {
			super();
			this.id = id;
			this.descricao = descricao;
			this.qtd_estoque = qtd_estoque;
			this.dt_cadastro = dt_cadastro;
			this.vlr_unitario = vlr_unitario;
			this.imagem = imagem;
			this.categoria = categoria;
		}
	
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getDescricao() {
			return descricao;
		}
	
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	
		public Integer getQtd_estoque() {
			return qtd_estoque;
		}
	
		public void setQtd_estoque(Integer qtd_estoque) {
			this.qtd_estoque = qtd_estoque;
		}
	
		public LocalDate getDt_cadastro() {
			return dt_cadastro;
		}
	
		public void setDt_cadastro(LocalDate dt_cadastro) {
			this.dt_cadastro = dt_cadastro;
		}
	
		public BigDecimal getVlr_unitario() {
			return vlr_unitario;
		}
	
		public void setVlr_unitario(BigDecimal vlr_unitario) {
			this.vlr_unitario = vlr_unitario;
		}
	
		public String getImagem() {
			return imagem;
		}
	
		public void setImagem(String imagem) {
			this.imagem = imagem;
		}
	
		public Categoria getCategoria() {
			return categoria;
		}
	
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public List<ItemPedido> getItem_pedido() {
			return item_pedido;
		}

		public void setItem_pedido(List<ItemPedido> item_pedido) {
			this.item_pedido = item_pedido;
		}
	
//		public ItemPedido getItem_pedido() {
//			return item_pedido;
//		}
//	
//		public void setItem_pedido(ItemPedido item_pedido) {
//			this.item_pedido = item_pedido;
//		}
	
		
	
    
	
}
