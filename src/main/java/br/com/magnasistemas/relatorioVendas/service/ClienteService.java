package br.com.magnasistemas.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.relatorioVendas.dto.ClienteDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesClienteDto;
import br.com.magnasistemas.relatorioVendas.entity.ClienteEntity;
import br.com.magnasistemas.relatorioVendas.entity.JogoEntity;
import br.com.magnasistemas.relatorioVendas.repository.ClienteRepository;
import br.com.magnasistemas.relatorioVendas.repository.JogoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	JogoRepository jogoRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<ClienteDTO> pegarTodos() {
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		List<ClienteEntity> clientes = clienteRepository.findAll();
		for (ClienteEntity clienteEntity : clientes) {
			ClienteDTO novo = modelMapper.map(clienteEntity, ClienteDTO.class);
			clientesDTO.add(novo);
		}
		return clientesDTO;
	}

	public DetalhesClienteDto buscarPorCpf(String cpf) {
		ClienteEntity cliente = clienteRepository.findByCpf(cpf);
		return  modelMapper.map(cliente, DetalhesClienteDto.class);
		
	}

	public void salvarClienteBanco(DetalhesClienteDto cliente) {
		List<JogoEntity> jogos = new ArrayList<>();
		cliente.getJogos().stream().forEach(jogo -> jogos.add(jogoRepository.findByLote(jogo.getLote())));
		ClienteEntity novo = modelMapper.map(cliente, ClienteEntity.class);
		novo.setJogos(jogos);
		clienteRepository.save(novo);
		//return modelMapper.map(novo, DetalhesClienteDto.class);
	}
	
}
