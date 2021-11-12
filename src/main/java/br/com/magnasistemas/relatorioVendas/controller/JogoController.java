package br.com.magnasistemas.relatorioVendas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.relatorioVendas.dto.DetalhesJogoDto;
import br.com.magnasistemas.relatorioVendas.dto.JogoDTO;
import br.com.magnasistemas.relatorioVendas.service.JogoService;

@RestController
@RequestMapping("/jogo")
public class JogoController {

	@Autowired
	JogoService jogoService;

	@GetMapping
	public List<JogoDTO> listarTodos() {
		return jogoService.pegarTodos();
	}

	@GetMapping("/{lote}")
	public DetalhesJogoDto buscarJogo(@PathVariable String lote) {
		return jogoService.buscarPorLote(lote);
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid DetalhesJogoDto jogo, UriComponentsBuilder uriBuilder) {
		try {
			jogoService.salvarJogoBanco(jogo);
			URI uri = uriBuilder.path("/jogo/{lote}").buildAndExpand(jogo.getLote()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("ERRO AO TENTAR CADASTRAR EMPRESA, VERIFIQUE OS DADOS!");
		}

	}	
}
