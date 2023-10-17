package com.agencia.model;

import java.math.BigDecimal;
import java.util.List;

import com.agencia.dao.ClientePacoteDAO;
import com.agencia.dao.PacoteDAO;
import com.agencia.utils.Colors;

public class Pacote {

	private int id;
	private boolean promocao;
	private BigDecimal valorTotal;
	private Hospedagem hospedagem;
	private Passagem passagem;

	public Pacote() {
		super();
	}

	public Pacote(boolean promocao, BigDecimal valorTotal, Hospedagem hospedagem, Passagem passagem) {
		super();
		this.promocao = promocao;
		this.valorTotal = valorTotal;
		this.hospedagem = hospedagem;
		this.passagem = passagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPromocao() {
		return promocao;
	}

	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Hospedagem getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(Hospedagem hospedagem) {
		this.hospedagem = hospedagem;
	}

	public Passagem getPassagem() {
		return passagem;
	}

	public void setPassagem(Passagem passagem) {
		this.passagem = passagem;
	}

	public void calculaValorTotal() {

	}
	
	public static void showDatas () {
		List<Pacote> pacotes = PacoteDAO.read("");

		System.out.println(
				Colors.YELLOW + "----------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s %20s %20s %20s %20s", "ID", "PROMOCÃO" , "VALOR TOTAL", "ID-HOSPEDAGEM", "ID-PASSAGEM");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		for (Pacote p : pacotes) {
			System.out.format("%5s %20s %20s %20s %20s",p.getId(), p.getPromocao(), p.getValorTotal(), p.getHospedagem().getId(), p.getPassagem().getId() );
			System.out.println();

		}
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------" + Colors.RESET);
	}
}
