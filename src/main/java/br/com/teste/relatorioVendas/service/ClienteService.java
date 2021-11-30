package br.com.teste.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.teste.relatorioVendas.dto.AlterarListas;
import br.com.teste.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.teste.relatorioVendas.dto.ClienteDTO;
import br.com.teste.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.teste.relatorioVendas.entity.ClienteEntity;
import br.com.teste.relatorioVendas.entity.JogoEntity;
import br.com.teste.relatorioVendas.repository.ClienteRepository;
import br.com.teste.relatorioVendas.repository.JogoRepository;

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
	public ResponseEntity<Page<ClienteDTO>> buscarTodos(Pageable pageable) {
		Page<ClienteEntity> clientes = clienteRepository.findAll(pageable);

		Page<ClienteDTO> clientesDTO = clientes.map(cliente -> modelMapper.map(cliente, ClienteDTO.class));

		return ResponseEntity.ok(clientesDTO);
	}

	// BUSCAR POR CPF
	public ResponseEntity<DetalhesClienteDTO> buscarPorCpf(String cpf) {
		ClienteEntity cliente = clienteRepository.findByCpf(cpf);
		return ResponseEntity.ok(modelMapper.map(cliente, DetalhesClienteDTO.class));
	}

	// SALVAR
	public void salvar(DetalhesClienteDTO cliente) {
		List<JogoEntity> jogos = new ArrayList<>();
		cliente.getJogos().stream().forEach(jogo -> jogos.add(jogoRepository.findByLote(jogo.getLote())));
		ClienteEntity novo = modelMapper.map(cliente, ClienteEntity.class);
		novo.setJogos(jogos);
		clienteRepository.save(novo);
		modelMapper.map(novo, DetalhesClienteDTO.class);
	}

	// ATUALIZAR
	public ResponseEntity<DetalhesClienteDTO> atualizar(String cpf, AtualizarClienteDTO cliente) {
		ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);
		clienteEntity.setTelefone(cliente.getTelefone());
		clienteRepository.save(clienteEntity);
		DetalhesClienteDTO novoClinte = modelMapper.map(clienteEntity, DetalhesClienteDTO.class);
		return ResponseEntity.ok(novoClinte);
	}

	// Adicionar 1 jogo ao cliente
	public ResponseEntity<AlterarListas> adicionarJogo(String cpf, AlterarListas cliente) {
		ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);
		JogoEntity novo = jogoRepository.findByLote(cliente.getLote());

		clienteEntity.getJogos().add(novo);
		clienteRepository.save(clienteEntity);

		return ResponseEntity.ok().build();
	}

	// Remover 1 jogo do cliente
	public ResponseEntity<AlterarListas> removerJogo(String cpf, AlterarListas cliente) {
		ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);
		JogoEntity novo = jogoRepository.findByLote(cliente.getLote());

		clienteEntity.getJogos().remove(novo);
		clienteRepository.save(clienteEntity);

		return ResponseEntity.ok().build();
	}

	// DELETAR POR CPF
	public void deletarPorCpf(String cpf) {
		clienteRepository.deleteByCpf(cpf);
	}
}
