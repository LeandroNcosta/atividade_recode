package com.agencia.menus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.agencia.dao.ClienteDAO;
import com.agencia.dao.ClientePacoteDAO;
import com.agencia.dao.PacoteDAO;
import com.agencia.model.Cliente;
import com.agencia.model.ClientePacote;
import com.agencia.model.Pacote;
import com.agencia.utils.Colors;

public class MenuPagamento {

	public static void menuPagamento(int subOpcao) throws ParseException {
		Scanner input = new Scanner(System.in);

		switch (subOpcao) {
		case 1:
			ClientePacote pagPacote = new ClientePacote();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			System.out.println("");
			System.out.println(Colors.YELLOW + " Informe os dados necessários: \n" + Colors.RESET);

			System.out.println(" Informe a data de compra (dd/MM/yyyy) ");
			String[] dateInfo = { "Ano", "Mês", "Dia" };
			List<String> date = new ArrayList<>();

			for (int i = 0; i < 3; i++) {
				System.out.printf(" Informe o %s: ", dateInfo[i]);
				date.add(input.next());
			}

			StringBuilder sb = new StringBuilder();
			for (String i : date)
				sb.append(i + "/");
			sb.deleteCharAt(sb.length() - 1);

			Date dataFormatada = formato.parse(sb.toString());
			pagPacote.setDataCompra(dataFormatada);

			System.out.println("\n TABELA CLIENTE");
			Cliente.showDatas();

			System.out.print(" Informe o id do cliente que fará parte da compra: ");
			pagPacote.setCliente(ClienteDAO.findBy(input.nextInt()));

			System.out.println("\n TABELA PACOTE");
			Pacote.showDatas();

			System.out.print(" Informe o Id do pacote que fará parte da compra");
			pagPacote.setPacote(PacoteDAO.findBy(input.nextInt()));

			ClientePacoteDAO.create(pagPacote);

			break;
		case 2:
			ClientePacote.showDatas();
			break;
		case 3:
			ClientePacote.showDatas();

			System.out.print("\n Informe o ID do pagamento que deseja atualizar: ");
			int id = input.nextInt();

			System.out.print(" Qual campo deseja atualizar ? (camelCase) ");
			String field;
			field = input.next();

			System.out.print(" Informe o novo valor para " + field + ": ");
			String value;
			value = input.next();

			ClientePacoteDAO.updateBy(id, field, value);
			break;
		case 4:
			System.out.println("\n TABELA CLIENTE_PACOTE");
			ClientePacote.showDatas();
			System.out.printf("\n Informe o ID da compra que deseja excluir : ");
			int deleteId = input.nextInt();

			System.out.print("\n Deseja excluir ? (y/n) ");
			String yesOrNo = input.next();

			if (yesOrNo.equalsIgnoreCase("y")) {
				ClientePacoteDAO.delete(deleteId);
			} else if (yesOrNo.equalsIgnoreCase("n")) {
				System.out.printf(" Compras (cliente-pacote) não foi excluído");
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
