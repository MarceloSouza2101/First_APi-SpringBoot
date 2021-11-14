package br.com.magnasistemas.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.relatorioVendas.dto.AtualizarJogoDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesJogoDTO;
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

	public Page<JogoDTO> pegarTodos(Pageable pageable) {
		List<JogoDTO> jogosDTO = new ArrayList<>();
		Page<JogoEntity> jogos = jogoRepository.findAll(pageable);
		for (JogoEntity jogoEntity : jogos) {
			JogoDTO novo = modelMapper.map(jogoEntity, JogoDTO.class);
			jogosDTO.add(novo);
		}

		Page<JogoDTO> page = new PageImpl<JogoDTO>(jogosDTO, pageable, 0);

		return page;
	}

	public DetalhesJogoDTO buscarPorLote(String lote) {
		JogoEntity jogo = jogoRepository.findByLote(lote);
		return modelMapper.map(jogo, DetalhesJogoDTO.class);
	}

	public void salvarJogoBanco(DetalhesJogoDTO jogo) {
		JogoEntity novoEntity = modelMapper.map(jogo, JogoEntity.class);
		jogoRepository.save(novoEntity);
		// return modelMapper.map(novoEntity, DetalhesJogoDto.class);
	}

	public ResponseEntity<DetalhesJogoDTO> atualizar(String lote, AtualizarJogoDTO jogo) {

		JogoEntity jogoEntity = jogoRepository.findByLote(lote);
		jogoEntity.setModalidade(jogo.getModalidade());
		jogoEntity.setDescricao(jogo.getDescricao());
		jogoRepository.save(jogoEntity);

		DetalhesJogoDTO novoJogoDto = modelMapper.map(jogoEntity, DetalhesJogoDTO.class);
		return ResponseEntity.ok(novoJogoDto);
	}

	public void deletarPorLote(String lote) {
		jogoRepository.deleteByLote(lote);
	}
}
