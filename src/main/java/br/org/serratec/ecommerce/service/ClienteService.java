package br.org.serratec.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.org.serratec.ecommerce.DTO.ClienteCadastroDTO;
import br.org.serratec.ecommerce.DTO.ClienteDTO;
import br.org.serratec.ecommerce.DTO.EnderecoDTO;
import br.org.serratec.ecommerce.config.Mapper;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Endereco;
import br.org.serratec.ecommerce.repository.ClienteRepository;
import br.org.serratec.ecommerce.repository.PedidoRepository;

@Service
public class ClienteService {
	@Autowired
	private CepService cepService;
	
	@Autowired
	private ClienteRepository clienterepositorio;
	@Autowired
	private PedidoRepository pedidorepositorio;
	@Autowired
	private EmailService emailService;
	
	public Page<ClienteDTO> obterTodosCliente(Pageable pageable){
		return clienterepositorio.findAll(pageable).map(c -> 
		ClienteDTO.toDto(c)
	);
	}
	
	public Optional<ClienteDTO> obterClientePorId(Long id) {
		Optional<Cliente> clienteEntity = clienterepositorio.findById(id);
		if (clienteEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(ClienteDTO.toDto(clienteEntity.get())); 
	}
	
	public ClienteDTO cadastraCliente(ClienteCadastroDTO cliente) {
		Cliente clienteEntity = cliente.toEntity();
		
		
		String json = cepService.obterDados(cliente.cep());
		EnderecoDTO enderecoViaCep;
		try {
			if (!clienterepositorio.existsByEmail(clienteEntity.getEmail()) 
					|| !clienterepositorio.existsByCpf(clienteEntity.getCpf())) {
				enderecoViaCep = Mapper.getMapper().readValue(json, EnderecoDTO.class);
				EnderecoDTO enderecoCompleto = new EnderecoDTO(enderecoViaCep.cep(), enderecoViaCep.rua(), 
						enderecoViaCep.bairro(), enderecoViaCep.cidade(), cliente.numero(), cliente.complemento(), 
						enderecoViaCep.uf());

				clienteEntity.setEndereco(enderecoCompleto.toEntity());
				
				emailService.enviarEmailTexto(clienteEntity.getEmail(), "Relatório de Pedido",
		                "Você está recebendo um email de cadastro");
				
				return ClienteDTO.toDto(clienterepositorio.save(clienteEntity));
				
				
		    }
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		return null;
	}
	
	public ClienteDTO gravarClientePorId(ClienteDTO cliente, EnderecoDTO endereco) {
		Cliente clienteEntity = cliente.toEntity();
		Endereco enderecoEntiry = endereco.toEntity();
		try {
			Optional<Endereco> EnderecoPorCep = clienterepositorio.findByEnderecoCep(endereco.cep());
			clienteEntity.setEndereco(EnderecoPorCep.get());
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException("Id do endereço é inválido");
		}
		
		clienterepositorio.save(clienteEntity);
		return ClienteDTO.toDto(clienteEntity);
	}
	
	public Optional<ClienteDTO> atualizarCliente(Long id, ClienteDTO cliente) {
		if (clienterepositorio.existsById(id)) {
			Cliente clienteEntity = cliente.toEntity();
			clienteEntity.setId(id);
			clienterepositorio.save(clienteEntity);
			return Optional.of(ClienteDTO.toDto(clienteEntity));
		}
		return Optional.empty();
	}
	
	public boolean excluirCliente (Long id) {
		Optional<Cliente> cliente = clienterepositorio.findById(id);
		if(cliente.isEmpty()) {
			return false;
		}
		clienterepositorio.deleteById(id);
		return true;
	}
}
