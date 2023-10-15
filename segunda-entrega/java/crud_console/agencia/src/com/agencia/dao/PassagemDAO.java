package com.agencia.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.agencia.model.Passagem;
import com.agencia.utils.Colors;

public class PassagemDAO {
	private static Connection conn;
	// private static Connection conn = Database.createConnection();
	private static String sql;
	private static ResultSet rset = null;

	public PassagemDAO(Connection conn) {
		this.conn = conn;
	}

	public static void create(Passagem passagem) {
		sql = "INSERT INTO passagem(id, compania,  idaEvolta, data, taxaServico, taxaEmbarque, tipoVoo, quantidade, promocao, cnpjCompania, valor, idDestino)"
				+ " VALUES(null, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?);";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, passagem.getCompania());
			stmt.setBoolean(2, passagem.getIdaEvolta());
			stmt.setTimestamp(3, new java.sql.Timestamp(0));
			stmt.setInt(4, 50); // default;
			stmt.setInt(5, 30); // default
			stmt.setString(6, passagem.getTipoVoo());
			stmt.setInt(7, passagem.getQuantidade());
			stmt.setBoolean(8, true); // default true;
			int n = (int) Math.ceil(Math.random());
			stmt.setString(9,  Integer.toString(n));
			stmt.setBigDecimal(10, new BigDecimal(200));
			stmt.setInt(11, passagem.getDestino().getId());

			stmt.executeUpdate();

			System.out.println(Colors.GREEN + " [log] Passagem criado com sucesso" + Colors.RESET);
		} catch (SQLException e) {
			System.out.println(Colors.RED + " [log] Erro ao criar passagem, Mensagem: " + e.getMessage() + Colors.RESET);
		} finally {

		}
	}

	public static List<Passagem> read(String pesquisa) {
		//sql = String.format("SELECT * FROM passagem WHERE id LIKE '%d%%' OR cnpjCompania '%s%%'", pesquisa, pesquisa);
		sql = String.format("SELECT * FROM passagem ", pesquisa, pesquisa);
		List<Passagem> passagens = new ArrayList<Passagem>();

		try (Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Passagem passagem = new Passagem();

				passagem.setId(resultSet.getInt("id"));
				passagem.setCompania(resultSet.getString("compania"));
				passagem.setIdaEvolta(resultSet.getBoolean("idaEvolta"));
				passagem.setData(resultSet.getTimestamp("data"));
				passagem.setTaxaServico(resultSet.getInt("taxaServico"));
				passagem.setTaxaEmbarque(resultSet.getInt("taxaEmbarque"));
				passagem.setTipoVoo(resultSet.getString("tipoVoo"));
				passagem.setQuantidade(resultSet.getInt("quantidade"));
				passagem.setPromocao(resultSet.getBoolean("promocao"));
				passagem.setCnpjCompania(resultSet.getString("cnpjCompania"));
				passagem.setValor(resultSet.getBigDecimal("valor"));
				passagem.setDestino(DestinoDAO.findBy(resultSet.getInt("idDestino")));

				// stmt.setTimestamp(3, new
				// java.sql.Timestamp(consulta.getDataConsulta().getTime()));
			}
			System.out.println(Colors.GREEN + " [log] Resultado retornado com sucesso" + Colors.RESET);
			System.out.println("");
			return passagens;
		} catch (SQLException e) {
			System.out.println(Colors.RED + "\n [log] Não foi possíevl ler os dados da tabela passagem. Message: " + e.getMessage() + "\n" + Colors.RESET);
			return null;
		}
	}

	public static void update(Passagem passagem) {
		sql = "UPDATE passagem SET compania = ?, idaEvolta = ?, data = ?, taxaServico = ?, taxaEmbarque = ?, tipoVoo = ?, quantidade = ?, promocao = ?, "
				+ "cnpjCompania = ?, valor = ?, idDestino = ? WHERE id = ? LIMIT 1";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, passagem.getCompania());
			stmt.setBoolean(2, passagem.getIdaEvolta());
			stmt.setTimestamp(3, new java.sql.Timestamp(passagem.getData().getTime()));
			stmt.setInt(4, passagem.getTaxaServico());
			stmt.setInt(5, passagem.getTaxaEmbarque());
			stmt.setString(6, passagem.getTipoVoo());
			stmt.setInt(7, passagem.getQuantidade());
			stmt.setBoolean(8, passagem.getPromocao());
			stmt.setString(9, passagem.getCnpjCompania());
			stmt.setBigDecimal(10, passagem.getValor());
			stmt.setInt(11, passagem.getDestino().getId());
			stmt.setInt(12, passagem.getId());
			stmt.executeUpdate();

			System.out.printf(Colors.GREEN + "\n [log] Passagem atualizada \n" + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf(Colors.RED + "\n [log] Erro ao atualizar passagem com o id : %d, Mensagem: %s %n", passagem.getId(),
					e.getMessage() + Colors.RESET);

		} finally {

		}
	}

	public static void delete(int id) {
		sql = "DELETE FROM passagem WHERE id = ? LIMIT 1";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println(Colors.GREEN + "\n [log] Passagem foi deletado com sucesso. \n" + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf(Colors.RED + "%n [log] Erro ao deletar passagem com o id : %d, Mensagem: %s %n", id,
					e.getMessage() + Colors.RED);

		} finally {

		}
	}

	public static Passagem findBy(int PassagemId) {
		sql = String.format("SELECT * FROM passagem WHERE id = %d or cnpjCompania = %s", PassagemId, Integer.toString(PassagemId) );
		Passagem passagem = new Passagem();

		try (Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				passagem.setId(resultSet.getInt("id"));
				passagem.setCompania(resultSet.getString("compania"));
				passagem.setIdaEvolta(resultSet.getBoolean("idaEvolta"));
				passagem.setData(resultSet.getTimestamp("data"));
				passagem.setTaxaServico(resultSet.getInt("taxaServico"));
				passagem.setTaxaEmbarque(resultSet.getInt("taxaEmbarque"));
				passagem.setTipoVoo(resultSet.getString("tipoVoo"));
				passagem.setQuantidade(resultSet.getInt("quantidade"));
				passagem.setPromocao(resultSet.getBoolean("promocao"));
				passagem.setCnpjCompania(resultSet.getString("cnpjCompania"));
				passagem.setValor(resultSet.getBigDecimal("valor"));
				passagem.setDestino(DestinoDAO.findBy(resultSet.getInt("idDestino")));
			}

			System.out.println(Colors.GREEN +"\n [log] Encontrado Passagem com sucesso \n" + Colors.RESET);

			return passagem;
		} catch (SQLException e) {
			System.out.println(Colors.RED + "\n [log] Não foi possível encontrar o passagem informado. Message: " + e.getMessage() + "\n" +Colors.RESET);
			return null;
		}

	}

	public void fecharConexao() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
