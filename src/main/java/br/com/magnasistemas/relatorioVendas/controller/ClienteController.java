package br.com.magnasistemas.relatorioVendas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.relatorioVendas.dto.ClienteDTO;
import br.com.magnasistemas.relatorioVendas.dto.DetalhesClienteDto;
import br.com.magnasistemas.relatorioVendas.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	
	@Autowired
	ClienteService  clienteService;
	
	@GetMapping
	public List<ClienteDTO> listarTodos() {
		return clienteService.pegarTodos();
	}
	
	@GetMapping("/{cpf}")
	public DetalhesClienteDto buscarCpf(@PathVariable String cpf) {
		return clienteService.buscarPorCpf(cpf);
	}
	
	
	@PostMapping
	public ResponseEntity<DetalhesClienteDto> cadastrar(@RequestBody @Valid DetalhesClienteDto cliente, UriComponentsBuilder uriBuilder){
		clienteService.salvarClienteBanco(cliente);
		URI uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(cliente.getCpf()).toUri();
		return ResponseEntity.created(uri).build();
	}

	
}
