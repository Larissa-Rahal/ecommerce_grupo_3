package br.org.serratec.ecommerce.DTO;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteCadastroDTO(
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
		Endereco endereco,
		@NotNull(message = "A data de nascimento não pode ser nula.")
		LocalDate dt_nasc,
		@NotBlank(message = "O cep não pode ser nulo.")
		String cep,
		@NotNull(message = "O numero não pode ser nulo.")
		Integer numero,
		@NotBlank(message = "O complemento não pode ser nulo.")
		String complemento) {

	public Cliente toEntity() {
		return Mapper.getMapper().convertValue(this, Cliente.class);
	}

}
