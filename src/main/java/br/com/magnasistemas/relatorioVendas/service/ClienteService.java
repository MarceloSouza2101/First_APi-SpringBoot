package br.com.magnasistemas.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.magnasistemas.relatorioVendas.dto.ClienteDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.magnasistemas.relatorioVendas.entity.ClienteEntity;
import br.com.magnasistemas.relatorioVendas.entity.JogoEntity;
import br.com.magnasistemas.relatorioVendas.repository.ClienteRepository;
import br.com.magnasistemas.relatorioVendas.repository.JogoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	MessageSource messageSource;

	@Autowired
	JogoRepository jogoRepository;

	@Autowired
	ModelMapper modelMapper;

	// PEGAR TODOS
	public Page<ClienteDTO> buscarTodos(Pageable pageable) {
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		Page<ClienteEntity> clientes = clienteRepository.findAll(pageable);
		for (ClienteEntity clienteEntity : clientes) {
			ClienteDTO novo = modelMapper.map(clienteEntity, ClienteDTO.class);
			clientesDTO.add(novo);
		}

		Page<ClienteDTO> page = new PageImpl<>(clientesDTO, pageable, 0);

		return page;
	}

	// BUSCAR POR CPF
	public DetalhesClienteDTO buscarPorCpf(String cpf) {
		ClienteEntity cliente = clienteRepository.findByCpf(cpf);
		return modelMapper.map(cliente, DetalhesClienteDTO.class);
	}

	// SALVAR
	public DetalhesClienteDTO salvar(DetalhesClienteDTO cliente) {
		List<JogoEntity> jogos = new ArrayList<>();
		cliente.getJogos().stream().forEach(jogo -> jogos.add(jogoRepository.findByLote(jogo.getLote())));
		ClienteEntity novo = modelMapper.map(cliente, ClienteEntity.class);
		novo.setJogos(jogos);
		clienteRepository.save(novo);
		return modelMapper.map(novo, DetalhesClienteDTO.class);
	}

	// ATUALIZAR
	public DetalhesClienteDTO atualizar(String cpf, AtualizarClienteDTO cliente) {
		ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);
		clienteEntity.setTelefone(cliente.getTelefone());
		clienteRepository.save(clienteEntity);
		DetalhesClienteDTO novoClinte = modelMapper.map(clienteEntity, DetalhesClienteDTO.class);
		return novoClinte;
	}

	// DELETAR POR CPF
	public void deletarPorCpf(String cpf) {
		clienteRepository.deleteByCpf(cpf);
	}
}
