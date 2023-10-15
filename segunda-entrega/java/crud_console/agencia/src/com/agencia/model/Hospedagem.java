package com.agencia.model;

import java.math.BigDecimal;
import java.util.Date;

public class Hospedagem {
	private int id;
	private String nome;
	private int estrelas;
	private String cnpj;
	private Boolean promocao;
	private BigDecimal valor;
	private String endereco;
	private Date dataEntrada;
	private Date dataSaida;

	public Hospedagem() {
		super();
	}

	public Hospedagem(String nome, int estrelas, String cnpj, Boolean promocao, BigDecimal valor, String endereco,
			Date dataEntrada, Date dataSaida) {
		super();
		this.nome = nome;
		this.estrelas = estrelas;
		this.cnpj = cnpj;
		this.promocao = promocao;
		this.valor = valor;
		this.endereco = endereco;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

}
