package com.agencia.menus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.agencia.dao.ClienteDAO;
import com.agencia.dao.HospedagemDAO;
import com.agencia.dao.PacoteDAO;
import com.agencia.dao.PassagemDAO;
import com.agencia.model.Cliente;
import com.agencia.model.Hospedagem;
import com.agencia.model.Pacote;
import com.agencia.model.Passagem;
import com.agencia.utils.Colors;

public class MenuPacote {

	public static void menuPacote(int subOpcao) {
		Scanner input = new Scanner(System.in);

		switch (subOpcao) {
		case 1:
			Pacote pacote = new Pacote();
			pacote.setPromocao(true);

			System.out.println("");
			System.out.println(Colors.YELLOW + " Informe os dados necessários: \n" + Colors.RESET);

			System.out.println("\n TABELA DE HOSPEDAGEM");
			Hospedagem.showDatas();

			System.out.print("\n Selecione o ID da hospedagem que fará parte do pacote: ");
			int idHospedagem = input.nextInt();
			Hospedagem h = HospedagemDAO.findBy(idHospedagem);
			pacote.setHospedagem(HospedagemDAO.findBy(idHospedagem));

			System.out.println("\n TABELA DE PASSAGEM");
			Passagem.showDatas();

			System.out.print(" Selecione o ID passagem que fará parte do pacote: ");
			int idPassagem = input.nextInt();
			Passagem p = PassagemDAO.findBy(idPassagem);
			pacote.setPassagem(PassagemDAO.findBy(idPassagem));

			// soma do valor total do pacote = valor passagem + valor hospedagem
			pacote.setValorTotal(h.getValor().add(p.getValor()));

			PacoteDAO.create(pacote);
			break;
		case 2:
			Pacote.showDatas();
			break;
		case 3:
			Pacote.showDatas();

			System.out.print(" Escolha o ID do pacote que deseja atualizar: ");
			int id = input.nextInt();

			System.out.print(" Qual campo deseja atualizar? (camelCase) ");
			String field;
			field = input.next();

			System.out.print(" Informe o novo valor para " + field + ": ");
			String value;
			value = input.next();

			PacoteDAO.updateBy(id, field, value);
			break;
		case 4:
			Pacote.showDatas();

			System.out.printf("\n Informe o ID do pacote que deseja excluir: ");
			int deleteId = input.nextInt();

			System.out.print("\n Deseja excluir? (y/n) ");
			String yesOrNo = input.next();

			if (yesOrNo.equalsIgnoreCase("y")) {
				PacoteDAO.delete(deleteId);
			} else if (yesOrNo.equalsIgnoreCase("n")) {
				System.out.printf(" Pacote não foi excluído");
			} else {
				System.out.printf("\n Tente novamente... \n");
			}
			break;
		case 5:
			break;
		default:
			System.out.printf("\n Tente novamente... \n");
		}

	}

}
