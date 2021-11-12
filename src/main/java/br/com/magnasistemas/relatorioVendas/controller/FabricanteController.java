package br.com.magnasistemas.relatorioVendas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.magnasistemas.relatorioVendas.dto.FabricanteDTO;
import br.com.magnasistemas.relatorioVendas.service.FabricanteService;

@RestController
@RequestMapping("/fabricante")
public class FabricanteController {

	@Autowired
	FabricanteService fabricanteService;

	@GetMapping
	public List<FabricanteDTO> listarTodos() {
		return fabricanteService.pegarTodos();
	}

	@GetMapping("/{cnpj}")
	public DetalhesFabricanteDTO buscarCnpj(@PathVariable String cnpj) {
		return fabricanteService.buscarPorCnpj(cnpj);
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid DetalhesFabricanteDTO fabricanteDto,
			UriComponentsBuilder builder) {
		try {
			fabricanteService.salvarFabricanteBanco(fabricanteDto);
			URI uri = builder.path("/fabricante/{cnpj}").buildAndExpand(fabricanteDto.getCnpj()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("ERRO AO TENTAR CADASTRAR EMPRESA, VERIFIQUE OS DADOS!");
		}
	}

	@PutMapping("/{cnpj}")
	public DetalhesFabricanteDTO atualizar(@PathVariable String cnpj,
			@RequestBody @Valid AtualizarFabricanteDTO fabrica) {
		return fabricanteService.atualizar(cnpj, fabrica);
	}

	@DeleteMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable String cnpj) {
		fabricanteService.deletarPorCnpj(cnpj);
		return ResponseEntity.ok().body("Fabricante deletado com sucesso");
	}
}
