package com.agencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.agencia.model.ClientePacote;
import com.agencia.utils.Colors;

public class ClientePacoteDAO {
	private static Connection conn;
	// private static Connection conn = Database.createConnection();
	private static String sql;
	private static ResultSet resultSet = null;

	public ClientePacoteDAO(Connection conn) {
		this.conn = conn;
	}

	public static void create(ClientePacote clientePacote) {
		sql = "INSERT INTO clientePacote(id, dataCompra, idCliente, idPacote) VALUES(null, ?, ?, ?);";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setTimestamp(1, new java.sql.Timestamp(clientePacote.getDataCompra().getTime()));
			stmt.setInt(2, clientePacote.getCliente().getId());
			stmt.setInt(3, clientePacote.getPacote().getId());

			stmt.executeUpdate();

			System.out.println(Colors.GREEN + " [log] ClientePacote criado com sucesso " + Colors.RESET);
		} catch (SQLException e) {
			System.out.println(Colors.RED + " [log] Erro ao criar clientePacote, Mensagem: " + e.getMessage());
		} finally {

		}
	}

	public static List<ClientePacote> read(String pesquisa) {
		sql = String.format("SELECT * FROM clientePacote");
		List<ClientePacote> pacotes = new ArrayList<ClientePacote>();

		try (Statement statement = conn.createStatement()) {
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				ClientePacote clientePacote = new ClientePacote();

				clientePacote.setId(resultSet.getInt("id"));
				clientePacote.setDataCompra(resultSet.getTimestamp("dataCompra"));
				clientePacote.setCliente(ClienteDAO.findBy(resultSet.getInt("idCliente")));
				clientePacote.setPacote(PacoteDAO.findBy(resultSet.getInt("idPacote")));

				pacotes.add(clientePacote);
			}
			System.out.println(Colors.GREEN + " [log] Resultado retornado com sucesso" + Colors.RESET);
			System.out.println("");
			return pacotes;
		} catch (SQLException e) {
			System.out.println(
					" [log] Não foi possível ler os dados da tabela clientePacote. Message: " + e.getMessage());
			return null;
		}
	}

	public static void update(ClientePacote clientePacote) {
		sql = "UPDATE clientePacote SET dataCompra = ?, idCliente = ?. idPacote = ? WHERE id = ? LIMIT 1";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setTimestamp(1, new java.sql.Timestamp(clientePacote.getDataCompra().getTime()));
			stmt.setInt(2, clientePacote.getCliente().getId());
			stmt.setInt(3, clientePacote.getPacote().getId());
			stmt.setInt(3, clientePacote.getId());

			stmt.executeUpdate();

			System.out.printf(Colors.GREEN + " [log] ClientePacote atualizada \n" + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf(" [log] Erro ao atualizar clientePacote com o id : %d, Mensagem: %s ",
					clientePacote.getId(), e.getMessage());

		} finally {

		}
	}

	public static void updateBy(int id, String field, String value) {
		sql = String.format("UPDATE clientePacote SET %s = '%s' WHERE id = %d", field.trim(), value.trim(), id);

		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);

			System.out.printf(Colors.GREEN + " [log] ClientePacote atualizado. " + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf(Colors.RED + " [log] Erro ao atualizar clientePacote com o id: %d, Mensagem: %s", id,
					e.getMessage());

		} finally {

		}
	}

	public static void delete(int id) {
		sql = "DELETE FROM clientePacote WHERE id = ? LIMIT 1";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println(Colors.GREEN + " [log] ClientePacote foi deletado com sucesso." + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf(Colors.RED + " [log] Erro ao deletar clientePacote com o id : %d, Mensagem: %s", id,
					e.getMessage() + Colors.RED);

		} finally {

		}
	}

	public static ClientePacote findBy(int clientePacoteId) {
		sql = String.format("SELECT * FROM clientePacote WHERE id = %d", clientePacoteId);
		ClientePacote clientePacote = new ClientePacote();

		try (Statement statement = conn.createStatement()) {
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				clientePacote.setId(resultSet.getInt("id"));
				clientePacote.setDataCompra(resultSet.getTimestamp("dataCompra"));
				clientePacote.setCliente(ClienteDAO.findBy(resultSet.getInt("idCliente")));
				clientePacote.setPacote(PacoteDAO.findBy(resultSet.getInt("idPacote")));

			}

			System.out.println(Colors.GREEN + " [log] Encontrado ClientePacote com sucesso." + Colors.RESET);

			return clientePacote;
		} catch (SQLException e) {
			System.out
					.println(" [log] Não foi possível encontrar o clientePacote informado. Message: " + e.getMessage());
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
