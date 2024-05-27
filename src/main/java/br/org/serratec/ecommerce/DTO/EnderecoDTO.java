package br.org.serratec.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Endereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDTO(
	
	String cep,
	@JsonAlias("logradouro") String rua,
	String bairro,
	@JsonAlias("localidade")String cidade,
	Integer numero,
	String complemento,
	String uf) {
	
	public Endereco toEntity() {
		return Mapper.getMapper().convertValue(this, Endereco.class);
	}
	
	public static EnderecoDTO toDto(Endereco enderecoEntity) {
		return Mapper.getMapper().convertValue(enderecoEntity, EnderecoDTO.class);
	}
}
