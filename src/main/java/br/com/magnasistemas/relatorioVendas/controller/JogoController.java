package br.com.magnasistemas.relatorioVendas.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.relatorioVendas.dto.AtualizarJogoDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesJogoDTO;
import br.com.magnasistemas.relatorioVendas.dto.JogoDTO;
import br.com.magnasistemas.relatorioVendas.service.JogoService;

@RestController
@RequestMapping("/jogo")
public class JogoController {

	@Autowired
	JogoService jogoService;

	@GetMapping
	public ResponseEntity<Page<JogoDTO>> listarTodos(
			@PageableDefault(sort = "lote", direction = Direction.ASC, page = 0, size = 2) Pageable pageable) {
		return ResponseEntity.ok(jogoService.pegarTodos(pageable));
	}

	@GetMapping("/{lote}")
	public DetalhesJogoDTO buscarJogo(@PathVariable String lote) {
		return jogoService.buscarPorLote(lote);
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid DetalhesJogoDTO jogo, UriComponentsBuilder uriBuilder) {

		jogoService.salvarJogoBanco(jogo);
		URI uri = uriBuilder.path("/jogo/{lote}").buildAndExpand(jogo.getLote()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("/{lote}")
	public DetalhesJogoDTO atualizar(@PathVariable String lote, @RequestBody @Valid AtualizarJogoDTO jogo) {
		return jogoService.atualizar(lote, jogo);
	}

	@DeleteMapping("/{lote}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable String lote) {
		jogoService.deletarPorLote(lote);
		return ResponseEntity.ok().body("Jogo deletado com sucesso");
	}
}
