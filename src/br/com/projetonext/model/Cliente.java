package br.com.projetonext.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

public class Cliente {

	private String Nome, Cpf, Email, Senha;

	public String getNome() {
		return this.Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getCpf() {
		return this.Cpf;
	}

	public void setCpf(String cpf) {
		this.Cpf = cpf;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String CriarConta(String NomeUsuario, String CpfUsuario, String EmailUsuario, String SenhaUsuario) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			String Resposta = MessageFormat.format(
					"INSERT INTO CLIENTES (NOME, CPF, EMAIL, SENHA) VALUES({0}, {1}, {2}, {3})",
					"'" + NomeUsuario + "'", "'" + CpfUsuario + "'", "'" + EmailUsuario + "'",
					"'" + SenhaUsuario + "'");
			statement.execute(Resposta);
			conn.close();
			return "Conta Criada!";

		} catch (Exception e) {
			System.out.print("Deu ruim, porque: " + e.getMessage());
			return "Deu ruim, porque: " + e.getMessage();
		}
	}

	public boolean BuscarContaCPF(String cpf, String senha) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CLIENTES WHERE CPF = '" + cpf + "'");
			String ResultadoNome = "", ResultadoCpf = "", ResultadoEmail = "";
			while (result.next()) {
				ResultadoNome = result.getString("NOME");
				ResultadoCpf = result.getString("CPF");
				ResultadoEmail = result.getString("EMAIL");
			}

			setNome(ResultadoNome);
			setCpf(ResultadoCpf);
			setEmail(ResultadoEmail);

			ResultSet pegar_senha = statement.executeQuery("SELECT SENHA FROM CLIENTES WHERE CPF= '" + cpf + "'");
			while (pegar_senha.next()) {
				Senha = pegar_senha.getString("SENHA");
			}

			if (senha.equals(Senha)) {
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}

		} catch (Exception e) {
			System.out.print("Deu ruim, porque: " + e.getMessage());
			return false;
		}
	}

	public boolean BuscarContaEmail(String email, String senha) {
		try {

			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CLIENTES WHERE EMAIL = '" + email + "'");
			String ResultadoNome = "", ResultadoCpf = "", ResultadoEmail = "";
			while (result.next()) {
				ResultadoNome = result.getString("NOME");
				ResultadoCpf = result.getString("CPF");
				ResultadoEmail = result.getString("EMAIL");
			}

			setNome(ResultadoNome);
			setCpf(ResultadoCpf);
			setEmail(ResultadoEmail);

			ResultSet pegar_senha = statement.executeQuery("SELECT SENHA FROM CLIENTES WHERE EMAIL= '" + email + "'");
			while (pegar_senha.next()) {
				Senha = pegar_senha.getString("SENHA");
			}

			if (senha.equals(Senha)) {
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}
		} catch (Exception e) {
			System.out.print("Deu ruim, porque: " + e.getMessage());
			return false;
		}
	}
}
