package com.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


@Entity
@Table(name="lancamento")

public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String descricao;
	
	@Column(name = "data_vencimento")
	private LocalDate dataPagamento;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamentoDate;
	
	private BigDecimal valor;
	private String observacao;
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	
	@ManyToOne
	@JoinColumn(name ="codigo_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name ="codigo_pessoa")
	private Pessoa pessoa;

	
	
	
	public Long getCodigo() {
		return codigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public LocalDate getDataPagamentoDate() {
		return dataPagamentoDate;
	}

	public void setDataPagamentoDate(LocalDate dataPagamentoDate) {
		this.dataPagamentoDate = dataPagamentoDate;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
