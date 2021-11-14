package br.com.magnasistemas.relatorioVendas.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class JogoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true) @NotNull @NotEmpty
	private String lote;
	private String nome;
	private String modalidade;
	private LocalDate lancamento;
	private String descricao;

	public JogoEntity() {
	}

	public JogoEntity(String lote, String nome, String modalidade, LocalDate lancamento, String descricao) {
		super();
		this.lote = lote;
		this.nome = nome;
		this.modalidade = modalidade;
		this.lancamento = lancamento;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, lancamento, lote, modalidade, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JogoEntity other = (JogoEntity) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(lancamento, other.lancamento) && Objects.equals(lote, other.lote)
				&& Objects.equals(modalidade, other.modalidade) && Objects.equals(nome, other.nome);
	}

}
