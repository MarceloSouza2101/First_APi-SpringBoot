package br.com.magnasistemas.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.relatorioVendas.dto.DetalhesFabricanteDto;
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
		if(fabricanteDTO.isEmpty()) {
		return fabricanteDTO;
		}
		return fabricanteDTO;
	}

	public DetalhesFabricanteDto buscarPorCnpj(String cnpj) {
		FabricanteEntity jogo = fabricanteRepository.findByCnpj(cnpj);
		return  modelMapper.map(jogo, DetalhesFabricanteDto.class);
		
	}

	public void salvarFabricanteBanco(DetalhesFabricanteDto fabricanteDto) {
		List<JogoEntity> jogos = new ArrayList<>();
		fabricanteDto.getJogos().stream().forEach(jogo -> jogos.add(jogoRepository.findByLote(jogo.getLote())));
		FabricanteEntity novo = modelMapper.map(fabricanteDto, FabricanteEntity.class);
		novo.setJogos(jogos);
		fabricanteRepository.save(novo);
		//return modelMapper.map(novo, DetalhesFabricanteDto.class);
	}
	
}
