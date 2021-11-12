package br.com.magnasistemas.relatorioVendas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.UnexpectedTypeException;
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

import br.com.magnasistemas.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.magnasistemas.relatorioVendas.dto.ClienteDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.magnasistemas.relatorioVendas.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public List<ClienteDTO> listarTodos() {
		return clienteService.buscarTodos();
	}

	@GetMapping("/{cpf}")
	public DetalhesClienteDTO buscarCpf(@PathVariable String cpf) {
		return clienteService.buscarPorCpf(cpf);
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid DetalhesClienteDTO cliente,
			UriComponentsBuilder uriBuilder) {
		try {
			clienteService.salvar(cliente);
			URI uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(cliente.getCpf()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (DataIntegrityViolationException | UnexpectedTypeException | NullPointerException e) {
			return ResponseEntity.badRequest().body("ERRO AO TENTAR CADASTRAR CLIENTE, VERIFIQUE OS DADOS!");
		}
	}

	@PutMapping("/{cpf}")
	public DetalhesClienteDTO atualizar(@PathVariable String cpf, @RequestBody @Valid AtualizarClienteDTO cliente) {
		return clienteService.atualizar(cpf, cliente);
	}

	@DeleteMapping("/{cpf}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable String cpf) {
		clienteService.deletarPorCpf(cpf);
		return ResponseEntity.ok().body("Cliente deletado com sucesso");
	}
}
