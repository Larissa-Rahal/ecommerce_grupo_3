package br.org.serratec.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.serratec.ecommerce.DTO.PedidoDTO;
import br.org.serratec.ecommerce.config.Mapper;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long id;
	
	private LocalDate dt_pedido; //data_pedido
	private LocalDate dt_entrega; //data_entrega
	private LocalDate dt_envio; //data_envio
	@Enumerated(EnumType.STRING)
	private Status status;
	@JsonIgnore
	private BigDecimal vlr_total; //valor_total
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> item_pedido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;	
	
	
	
	public Pedido () {}

	public Pedido(Long id, LocalDate dt_pedido, LocalDate dt_entrega, LocalDate dt_envio, Status status,
			BigDecimal vlr_total, Cliente cliente) {
		super();
		this.id = id;
		this.dt_pedido = dt_pedido;
		this.dt_entrega = dt_entrega;
		this.dt_envio = dt_envio;
		this.status = status;
		this.vlr_total = vlr_total;
		this.cliente = cliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDt_pedido() {
		return dt_pedido;
	}
	public void setDt_pedido(LocalDate dt_pedido) {
		this.dt_pedido = LocalDate.now();
	}
	public LocalDate getDt_entrega() {
		return dt_entrega;
	}
	public void setDt_entrega(LocalDate dt_entrega) {
		this.dt_entrega = LocalDate.now().plusDays(10);
	}
	public LocalDate getDt_envio() {
		return dt_envio;
	}
	public void setDt_envio(LocalDate dt_envio) {
		this.dt_envio = LocalDate.now().plusDays(3);
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getVlr_total() {
		return vlr_total;
	}

	public void setVlr_total(BigDecimal vlr_total) {
		this.vlr_total = vlr_total;
	}

	public List<ItemPedido> getItem_pedido() {
		return item_pedido;
	}

	public void setItem_pedido(List<ItemPedido> item_pedido) {
		this.item_pedido = item_pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	

}
