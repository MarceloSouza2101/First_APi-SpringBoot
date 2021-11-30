package br.com.teste.relatorioVendas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.teste.relatorioVendas.dto.AlterarListas;
import br.com.teste.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.teste.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.teste.relatorioVendas.dto.FabricanteDTO;
import br.com.teste.relatorioVendas.service.FabricanteService;

@RestController
@RequestMapping("/fabricante")
public class FabricanteController {

	@Autowired
	FabricanteService fabricanteService;

	@GetMapping
	public ResponseEntity<Page<FabricanteDTO>> listarTodos(
			@PageableDefault(sort = "cnpj", direction = Direction.ASC, page = 0, size = 2) Pageable pageable) {
		return ResponseEntity.ok(fabricanteService.pegarTodos(pageable));
	}

	@GetMapping("/{cnpj}")
	public DetalhesFabricanteDTO buscarCnpj(@PathVariable String cnpj) {
		return fabricanteService.buscarPorCnpj(cnpj);
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid DetalhesFabricanteDTO fabricanteDto,
			UriComponentsBuilder builder) {

		fabricanteService.salvarFabricanteBanco(fabricanteDto);
		URI uri = builder.path("/fabricante/{cnpj}").buildAndExpand(fabricanteDto.getCnpj()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{cnpj}")
	public DetalhesFabricanteDTO atualizar(@PathVariable String cnpj,
			@RequestBody @Valid AtualizarFabricanteDTO fabrica) {
		return fabricanteService.atualizar(cnpj, fabrica);
	}
	
	@PatchMapping("/adicionar/{cnpj}")
	public ResponseEntity<AlterarListas> adicionarJogoCliente(@PathVariable String cnpj,
			@RequestBody @Valid AlterarListas cliente) {
		return fabricanteService.adicionarJogo(cnpj, cliente);
	}
	
	@PatchMapping("/remover/{cnpj}")
	public ResponseEntity<AlterarListas> removerJogoCLiente(@PathVariable String cnpj,
			@RequestBody @Valid AlterarListas fabricante) {
		return fabricanteService.removerJogo(cnpj, fabricante);
	}

	@DeleteMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable String cnpj) {
		fabricanteService.deletarPorCnpj(cnpj);
		return ResponseEntity.ok().body("Fabricante deletado com sucesso");
	}
}
