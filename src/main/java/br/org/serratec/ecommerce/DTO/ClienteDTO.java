package br.org.serratec.ecommerce.DTO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Endereco;
import br.org.serratec.ecommerce.entity.Pedido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
	Long id,
	@Email
	@NotBlank(message = "O email não pode ser nulo.")
	String email,
	@NotBlank(message = "O nome não pode ser nulo.")
	String nome,
	@CPF
	@NotBlank(message = "O cpf não pode ser nulo.")
	String cpf,
	@NotBlank(message = "O telefone não pode ser nulo.")
	String telefone,
	@NotBlank(message = "O endereço não pode ser nulo.")
	Endereco endereco,
	@NotBlank(message = "A data de nascimento não pode ser nula.")
	LocalDate dt_nasc,
	@JsonIgnore
	Long pedidoId){
	
	public Cliente toEntity() {
		return Mapper.getMapper().convertValue(this, Cliente.class);
	}
	
	public static ClienteDTO toDto(Cliente clienteEntity) {
		return Mapper.getMapper().convertValue(clienteEntity, ClienteDTO.class);
	}
	

}
