package br.com.magnasistemas.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.magnasistemas.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.magnasistemas.relatorioVendas.dto.FabricanteDTO;
import br.com.magnasistemas.relatorioVendas.entity.FabricanteEntity;
import br.com.magnasistemas.relatorioVendas.entity.JogoEntity;
import br.com.magnasistemas.relatorioVendas.repository.FabricanteRepository;
import br.com.magnasistemas.relatorioVendas.repository.JogoRepository;

@Service
public class FabricanteService {

	@Autowired
	FabricanteRepository fabricanteRepository;

	@Autowired
	JogoRepository jogoRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<FabricanteDTO> pegarTodos() {
		List<FabricanteDTO> fabricanteDTO = new ArrayList<>();
		List<FabricanteEntity> fabricante = fabricanteRepository.findAll();
		for (FabricanteEntity fabricanteEntity : fabricante) {
			FabricanteDTO novo = modelMapper.map(fabricanteEntity, FabricanteDTO.class);
			fabricanteDTO.add(novo);
		}
		return fabricanteDTO;
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
}
