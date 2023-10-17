package com.agencia.menus;

import java.util.Scanner;

import com.agencia.dao.DestinoDAO;
import com.agencia.dao.PassagemDAO;
import com.agencia.model.Destino;
import com.agencia.model.Passagem;
import com.agencia.utils.Colors;

public class MenuPassagem {

	public static void menuPassagem(int subOpcao) {
		Scanner input = new Scanner(System.in);

		switch (subOpcao) {
		case 1:
			Passagem passagem = new Passagem();

			System.out.println(Colors.YELLOW + "\n Informe os dados necessários: \n" + Colors.RESET);
			System.out.print(" Deseja de qual compania (Ex: Latam, Gol, Azul..) ? ");
			passagem.setCompania(input.next());

			String yesOrNo = "";

			while (!(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("n"))) {
				System.out.print(" Deseja passagem de volta?: (y/n) ");
				yesOrNo = input.next();
				if (yesOrNo.equalsIgnoreCase("y")) {
					passagem.setIdaEvolta(true);
				} else if (yesOrNo.equalsIgnoreCase("n")) {
					passagem.setIdaEvolta(false);
				} else {
					System.out.println(" Tente novamente...");
				}
			}

			System.out.print(" Deseja qual tipo de voo? [1] Direto [2] Escala [3] Conexão ");
			int tipoVoo = input.nextInt();

			if (tipoVoo == 1) {
				passagem.setTipoVoo("direto");
			} else if (tipoVoo == 2) {
				passagem.setTipoVoo("escala");
			} else {
				passagem.setTipoVoo("conexao");
			}

			System.out.print(" Informe a quantidade: ");
			passagem.setQuantidade(input.nextInt());

			System.out.println("\n TABELA DESTINO");
			Destino.showDatas();

			System.out.print(" Informe o ID do destino que fará parte da passagem: ");
			int destinoId = input.nextInt();
			passagem.setDestino(DestinoDAO.findBy(destinoId));

			PassagemDAO.create(passagem);
			break;
		case 2:
			Passagem.showDatas();
			break;
		case 3:
			System.out.println("\n TABELA PASSAGEM");
			Passagem.showDatas();
			System.out.print(" Escolha o ID da passagem: que deseja atualizar: ");
			int id = input.nextInt();

			System.out.print(" Qual campo deseja atualizar? (camelCase) ");
			String field;
			field = input.next();

			System.out.print(" Informe o novo valor para " + field + ": ");
			String value;
			value = input.next();

			PassagemDAO.updateBy(id, field, value);
			break;
		case 4:
			System.out.println("\n TABELA PASSAGEM");
			Passagem.showDatas();
			System.out.printf("\n Informe o ID da passagem que deseja excluir: ");
			int deleteId = input.nextInt();

			System.out.print("\n Deseja excluir? (y/n) ");
			yesOrNo = input.next();

			if (yesOrNo.equalsIgnoreCase("y")) {
				PassagemDAO.delete(deleteId);
			} else if (yesOrNo.equalsIgnoreCase("n")) {
				System.out.printf(" Passagem não foi excluído");
			} else {
				System.out.printf("\n Tente novamente... \n");
			}
		case 5:

			break;
		default:
			System.out.println("\n Tente novamente... \n");
		}

	}
}
