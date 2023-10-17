package com.agencia.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.agencia.dao.ClientePacoteDAO;
import com.agencia.dao.PassagemDAO;
import com.agencia.utils.Colors;

public class Passagem {
	private int id;
	private String compania;
	private Boolean idaEvolta;
	private Date data;
	private int taxaServico;
	private int taxaEmbarque;
	private String tipoVoo;
	private int quantidade;
	private Boolean promocao;
	private String cnpjCompania;
	private BigDecimal valor;
	private Destino destino;

	public Passagem() {
		super();
	}

	public Passagem(String compania, Boolean idaEvolta, Date data, int taxaServico, int taxaEmbarque, String tipoVoo,
			int quantidade, Boolean promocao, String cnpjCompania, BigDecimal valor, Destino destino) {
		super();
		this.compania = compania;
		this.idaEvolta = idaEvolta;
		this.data = data;
		this.taxaServico = taxaServico;
		this.taxaEmbarque = taxaEmbarque;
		this.tipoVoo = tipoVoo;
		this.quantidade = quantidade;
		this.promocao = promocao;
		this.cnpjCompania = cnpjCompania;
		this.valor = valor;
		this.destino = destino;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public Boolean getIdaEvolta() {
		return idaEvolta;
	}

	public void setIdaEvolta(Boolean idaEvolta) {
		this.idaEvolta = idaEvolta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getTaxaServico() {
		return taxaServico;
	}

	public void setTaxaServico(int taxaServico) {
		this.taxaServico = taxaServico;
	}

	public int getTaxaEmbarque() {
		return taxaEmbarque;
	}

	public void setTaxaEmbarque(int taxaTaxaEmbarque) {
		this.taxaEmbarque = taxaTaxaEmbarque;
	}

	public String getTipoVoo() {
		return tipoVoo;
	}

	public void setTipoVoo(String tipoVoo) {
		this.tipoVoo = tipoVoo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}

	public String getCnpjCompania() {
		return cnpjCompania;
	}

	public void setCnpjCompania(String cnpjCompania) {
		this.cnpjCompania = cnpjCompania;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	
	public static void showDatas () {
		List<Passagem> passagens = PassagemDAO.read("");

		System.out.println(
				Colors.YELLOW + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s %20s %20s %20s %20s %20s %20s %20s %20s %20s %20s %20s", "ID", "COMPANIA", "IDA E VOLTA?", "DATA", "TAXASERVICO", "TAXAEMBARQUE", "TIPO-VOO", "QUANTIDADE", "PROMOCAO", "CNPJ-COMPANIA", "VALOR", "ID-DESTINO");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Passagem p : passagens) {
			System.out.format("%5s %20s %20s %20s %20s %20s %20s %20s %20s %20s %20s %20s", p.getId(), p.getCompania(), p.id, p.getData(), p.getTaxaServico(), p.getTaxaEmbarque(), p.getTipoVoo(), p.getQuantidade(), p.getPromocao(), p.getCnpjCompania(), p.getValor(), p.getDestino().getId());
			System.out.println();
		}
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" + Colors.RESET);
	}

}
