/*
package br.org.serratec.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.DTO.EnderecoDTO;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Endereco;
import br.org.serratec.ecommerce.repository.ClienteRepository;

@Service
public class EnderecoService {
	
	//@Autowired
	//private ClienteRepository clienterepositorio;	
	
	public List<EnderecoDTO> obterTodosEnderecos(){		
		return enderecorepositorio.findAll().stream().map(c -> EnderecoDTO.toDto(c))
				.toList();
	}
	
	public Optional<EnderecoDTO> obterEnderecoPorId(Long id) {
		Optional<Endereco> enderecoEntity = enderecorepositorio.findById(id);
		if (enderecoEntity.isEmpty()) {
			return Optional.of(EnderecoDTO.toDto(enderecoEntity.get()));
		}
		return Optional.empty();
	}
	
	public EnderecoDTO cadastraEndereco(EnderecoDTO endereco) {
		Endereco enderecoEntity = enderecorepositorio.save(endereco.toEntity());
		return EnderecoDTO.toDto(enderecoEntity);
	}
	
//	public EnderecoDTO gravarEnderecoPorId(EnderecoDTO Endereco) {
//		Endereco EnderecoEntity = Endereco.toEntity();
//		try {
//			Optional<Cliente> clientePorId = clienterepositorio.findById(Endereco.clienteId());
//			EnderecoEntity.setCliente(clientePorId.get());
//		} catch (NoSuchElementException ex) {
//			throw new IllegalArgumentException("Id do cliente é inválido");
//		}
//		
//		enderecorepositorio.save(EnderecoEntity);
//		return EnderecoDTO.toDto(EnderecoEntity);
//	}
	
	public Optional<EnderecoDTO> atualizarEndereco(Long id, EnderecoDTO endereco) {
		if (enderecorepositorio.existsById(id)) {
			Endereco enderecoEntity = endereco.toEntity();
			//enderecoEntity.setId(id);
			enderecorepositorio.save(enderecoEntity);
			return Optional.of(EnderecoDTO.toDto(enderecoEntity));
		}
		return Optional.empty();
	}
	
	public boolean excluirEndereco (Long id) {
		Optional<Endereco> endereco = enderecorepositorio.findById(id);
		if(endereco.isEmpty()) {
			return false;
		}
		enderecorepositorio.deleteById(id);
		return true;
	}
}
*/