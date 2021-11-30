package br.com.teste.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.teste.relatorioVendas.dto.AlterarListas;
import br.com.teste.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.teste.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.teste.relatorioVendas.dto.FabricanteDTO;
import br.com.teste.relatorioVendas.entity.FabricanteEntity;
import br.com.teste.relatorioVendas.entity.JogoEntity;
import br.com.teste.relatorioVendas.repository.FabricanteRepository;
import br.com.teste.relatorioVendas.repository.JogoRepository;

@Service
public class FabricanteService {

	@Autowired
	FabricanteRepository fabricanteRepository;

	@Autowired
	JogoRepository jogoRepository;

	@Autowired
	ModelMapper modelMapper;

	public Page<FabricanteDTO> pegarTodos(Pageable pageable) {
		List<FabricanteDTO> fabricanteDTO = new ArrayList<>();
		Page<FabricanteEntity> fabricante = fabricanteRepository.findAll(pageable);
		for (FabricanteEntity fabricanteEntity : fabricante) {
			FabricanteDTO novo = modelMapper.map(fabricanteEntity, FabricanteDTO.class);
			fabricanteDTO.add(novo);
		}

		Page<FabricanteDTO> page = new PageImpl<>(fabricanteDTO, pageable, 0);

		return page;
	}

	public DetalhesFabricanteDTO buscarPorCnpj(String cnpj) {
		FabricanteEntity fabricante = fabricanteRepository.findByCnpj(cnpj);
		return modelMapper.map(fabricante, DetalhesFabricanteDTO.class);
	}

	public DetalhesFabricanteDTO salvarFabricanteBanco(DetalhesFabricanteDTO fabricanteDto) {
		List<JogoEntity> jogos = new ArrayList<>();
		fabricanteDto.getJogos().stream().forEach(jogo -> jogos.add(jogoRepository.findByLote(jogo.getLote())));
		FabricanteEntity novo = modelMapper.map(fabricanteDto, FabricanteEntity.class);
		novo.setJogos(jogos);
		fabricanteRepository.save(novo);
		return modelMapper.map(novo, DetalhesFabricanteDTO.class);
	}

	public DetalhesFabricanteDTO atualizar(String cnpj, AtualizarFabricanteDTO fabrica) {

		FabricanteEntity fabricanteEntity = fabricanteRepository.findByCnpj(cnpj);
		fabricanteEntity.setNome(fabrica.getNome());
		fabricanteRepository.save(fabricanteEntity);

		DetalhesFabricanteDTO novaFabricante = modelMapper.map(fabricanteEntity, DetalhesFabricanteDTO.class);
		return novaFabricante;

	}

	public void deletarPorCnpj(String cnpj) {
		fabricanteRepository.deleteByCnpj(cnpj);
	}

	//adicionando 1 jogo ao fabricante
	public ResponseEntity<AlterarListas> adicionarJogo(String cnpj, @Valid AlterarListas cliente) {
		FabricanteEntity fabricanteEntity = fabricanteRepository.findByCnpj(cnpj);
		JogoEntity novo = jogoRepository.findByLote(cliente.getLote());

		fabricanteEntity.getJogos().add(novo);
		fabricanteRepository.save(fabricanteEntity);

		return ResponseEntity.ok().build();

	}
	
	// Remover 1 jogo do Fabricante
		public ResponseEntity<AlterarListas> removerJogo(String cnpj, AlterarListas fabricante) {
			FabricanteEntity fabricanteEntity = fabricanteRepository.findByCnpj(cnpj);
			JogoEntity novo = jogoRepository.findByLote(fabricante.getLote());

			fabricanteEntity.getJogos().remove(novo);
			fabricanteRepository.save(fabricanteEntity);

			return ResponseEntity.ok().build();
		}
}
