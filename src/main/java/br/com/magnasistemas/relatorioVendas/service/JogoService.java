package br.com.magnasistemas.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.relatorioVendas.dto.DetalhesJogoDto;
import br.com.magnasistemas.relatorioVendas.dto.JogoDTO;
import br.com.magnasistemas.relatorioVendas.entity.JogoEntity;
import br.com.magnasistemas.relatorioVendas.repository.JogoRepository;

@Service
public class JogoService {

	@Autowired
	JogoRepository jogoRepository;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ModelMapper modelMapper;

	public List<JogoDTO> pegarTodos() {
		List<JogoDTO> jogosDTO = new ArrayList<>();
		List<JogoEntity> jogos = jogoRepository.findAll();
		for (JogoEntity jogoEntity : jogos) {
			JogoDTO novo = modelMapper.map(jogoEntity, JogoDTO.class);
			jogosDTO.add(novo);
		}
		if(jogosDTO.isEmpty()) {
		return jogosDTO;
		}
		return jogosDTO;
	}
	public DetalhesJogoDto buscarPorLote(String lote) {
		JogoEntity jogo = jogoRepository.findByLote(lote);
		return  modelMapper.map(jogo, DetalhesJogoDto.class);
	}
	
	
	 public void salvarJogoBanco(DetalhesJogoDto jogo) {
		 JogoEntity novoEntity = modelMapper.map(jogo, JogoEntity.class);
		 jogoRepository.save(novoEntity);
		 //return modelMapper.map(novoEntity, DetalhesJogoDto.class);
	 }
		
	
}
