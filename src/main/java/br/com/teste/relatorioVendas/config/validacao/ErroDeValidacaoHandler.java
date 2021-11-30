package br.com.teste.relatorioVendas.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.teste.relatorioVendas.dto.ErroDeFormularioDTO;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDTO> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDTO erro = new ErroDeFormularioDTO(e.getField(), mensagem);
			dto.add(erro);
		});
		return dto;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> pegandoExcecoes(IllegalArgumentException exception) {
		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> pegandoExcecoes(Exception exception) {
		return ResponseEntity.badRequest()
				.body("Ocorreu um erro ao tentar executar a acão!\n" + "Verifique os dados e tente novamente!");
	}

}
