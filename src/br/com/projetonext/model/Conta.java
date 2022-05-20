package br.com.projetonext.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

import br.com.projetonext.controller.ContaController;
import br.com.projetonext.view.Application;

public class Conta {

	private String NumeroConta;
	private double SaldoConta;

	public String getNumeroConta() {
		return this.NumeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.NumeroConta = numeroConta;
	}

	public double getSaldoConta() {
		return this.SaldoConta;
	}

	public void setSaldoConta(double saldoConta) {
		this.SaldoConta = saldoConta;
	}

	public String CriarConta(String numeroconta, double saldo) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet VerificarContaCpf = statement.executeQuery("SELECT NUMERO FROM CONTA");
			String RespostaNumero = "";
			while (VerificarContaCpf.next()) {
				RespostaNumero = VerificarContaCpf.getString("NUMERO");
				if (RespostaNumero.equals(RespostaNumero)) {
					ContaController CC = new ContaController();
					CC.CriarContaControl();
				}
			}
			String RespostaMensagem = MessageFormat.format("INSERT INTO CONTA(NUMERO, SALDO) VALUES({0}, {1})",
					numeroconta, saldo);
			statement.execute(RespostaMensagem);
			conn.close();
			return "Conta Criada!";

		} catch (Exception e) {
			return "Deu ruim, porque: " + e.getMessage();
		}
	}

	public boolean BuscarContaCpf(String Cpf) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet VerificarContaCpf = statement
					.executeQuery("SELECT * FROM CONTA WHERE ID = (SELECT ID FROM CLIENTES WHERE CPF ='" + Cpf + "')");
			String RespostaNumero = "";
			double RespostaSaldo = 0.00;
			while (VerificarContaCpf.next()) {
				RespostaSaldo = VerificarContaCpf.getDouble("SALDO");
				RespostaNumero = VerificarContaCpf.getString("NUMERO");
			}

			setNumeroConta(RespostaNumero);
			setSaldoConta(RespostaSaldo);
			conn.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean BuscarContaEmail(String email) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet VerificarContaEmail = statement.executeQuery(
					"SELECT * FROM CONTA WHERE ID = (SELECT ID FROM CLIENTES WHERE EMAIL ='" + email + "')");
			String RespostaNumero = "";
			double RespostaSaldo = 0.00;
			while (VerificarContaEmail.next()) {
				RespostaSaldo = VerificarContaEmail.getDouble("SALDO");
				RespostaNumero = VerificarContaEmail.getString("NUMERO");
			}

			setNumeroConta(RespostaNumero);
			setSaldoConta(RespostaSaldo);
			conn.close();
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public String Depositar(String numeroConta, double depositarValor) {
		br.com.projetonext.controller.ContaController ContaControl = new br.com.projetonext.controller.ContaController();

		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet SaldoConta = statement
					.executeQuery("SELECT SALDO FROM CONTA WHERE NUMERO = '" + numeroConta + "'");
			double ResultadoSaldoConta = 0;
			while (SaldoConta.next()) {
				ResultadoSaldoConta = SaldoConta.getDouble("SALDO");
			}
			String RespostaMensagem = MessageFormat.format("UPDATE CONTA SET SALDO = ({0} + {1}) WHERE NUMERO = {2}",
					depositarValor, ResultadoSaldoConta, "'" + numeroConta + "'");
			statement.execute(RespostaMensagem);
			conn.close();
			String Deposito = "R$" + depositarValor + " Foi depositado na conta " + numeroConta;
			System.out.print(Deposito);
			Application.SaldoUsuario = ResultadoSaldoConta + depositarValor;
			ContaControl.VoltaMenuConta();
			return Deposito;
		} catch (Exception e) {
			return "Deu ruim, porque: " + e.getMessage();
		}
	}

	public String SacarDinheiro(String NumeroConta, double ValorSaque) {
		br.com.projetonext.controller.ContaController ContaControl = new br.com.projetonext.controller.ContaController();
		try {

			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();

			ResultSet VerificarSaldo = statement
					.executeQuery("SELECT SALDO FROM CONTA WHERE NUMERO= '" + NumeroConta + "'");
			double resultado_Valor = 0;

			while (VerificarSaldo.next()) {
				resultado_Valor = VerificarSaldo.getDouble("SALDO");
			}
			if (ValorSaque > resultado_Valor || ValorSaque < 0) {
				String Saldo = "Você não pode sacar mais do que: " + resultado_Valor;
				System.out.print(Saldo);
				ContaControl.VoltaMenuConta();
				return Saldo;

			} else {
				String RespostaMensagem = MessageFormat.format(
						"UPDATE CONTA SET SALDO = (SALDO  - {0}) WHERE NUMERO = {1};", ValorSaque,
						"'" + NumeroConta + "'");
				statement.execute(RespostaMensagem);
				conn.close();
				String Sacou = "Você sacou R$" + ValorSaque;
				System.out.print(Sacou);
				Application.SaldoUsuario = resultado_Valor - ValorSaque;
				ContaControl.VoltaMenuConta();
				return Sacou;
			}

		} catch (Exception e) {
			return "Deu ruim, porque: " + e.getMessage();
		}
	}

	public String Transferencia(String NumeroRemetente, String NuMeroDestinario, double ValorTransferencia) {
		try {
			br.com.projetonext.controller.ContaController ContaControl = new br.com.projetonext.controller.ContaController();
			Connection conn = DriverManager.getConnection("jdbc:h2:~/FiservBD", "Carlos", "");
			Statement statement = conn.createStatement();
			ResultSet VerificarContaDestinatario = statement
					.executeQuery("SELECT NUMERO FROM CONTA WHERE NUMERO= '" + NuMeroDestinario + "'");
			String resultado_verificacaoDestinatario = "";

			while (VerificarContaDestinatario.next()) {
				resultado_verificacaoDestinatario = VerificarContaDestinatario.getString("NUMERO");
			}
			if (resultado_verificacaoDestinatario.equals("")) {
				String NumeroNaoExiste = "Número de conta não existe";
				System.out.print(NumeroNaoExiste);

				ContaControl.VoltaMenuConta();
				return NumeroNaoExiste;
			} else {
				ResultSet VerificarSaldo = statement
						.executeQuery("SELECT SALDO FROM CONTA WHERE NUMERO= '" + NumeroRemetente + "'");
				double resultado_verificacaoSaldoRemetente = 0;
				while (VerificarSaldo.next()) {
					resultado_verificacaoSaldoRemetente = VerificarSaldo.getDouble("SALDO");
				}
				if (ValorTransferencia > resultado_verificacaoSaldoRemetente) {
					String NaoPodeRealizarTransferencia = "Voce nao pode realizar a transferencia!";
					System.out.print(NaoPodeRealizarTransferencia);
					ContaControl.VoltaMenuConta();
					return NaoPodeRealizarTransferencia;
				} else {

					String DescontarRemetente = MessageFormat.format(
							"UPDATE CONTA SET SALDO = (SALDO - {0}) WHERE NUMERO = {1}", ValorTransferencia,
							"'" + NumeroRemetente + "'");
					statement.execute(DescontarRemetente);

					String AdicionarDestinatario = MessageFormat.format(
							"UPDATE CONTA SET SALDO = (SALDO + {0}) WHERE NUMERO = {1}", ValorTransferencia,
							"'" + NuMeroDestinario + "'");
					statement.execute(AdicionarDestinatario);

					String RealizouTransferencia = NumeroRemetente + " Depositou R$" + ValorTransferencia
							+ " para a conta " + NuMeroDestinario;
					Application.SaldoUsuario = (resultado_verificacaoSaldoRemetente - ValorTransferencia);
					conn.close();
					System.out.print(RealizouTransferencia);
					
					return "transferencia realizada!";
				}
			}
		} catch (Exception e) {
			return "deu erro " + e;
		}

	}
}
