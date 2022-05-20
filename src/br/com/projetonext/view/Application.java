package br.com.projetonext.view;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Scanner;

import br.com.projetonext.controller.ClienteController;
import br.com.projetonext.controller.ContaController;
import br.com.projetonext.controller.Ligacao;

public class Application {

	public static String NomeUsuario, CpfUsuario, EmailUsuario, NumeroUsuario;
	public static double SaldoUsuario;

	public static void main(String[] args) {
		Next();
	}

	public static void Next() {
		Utils.Utilidades util = new Utils.Utilidades();
		Scanner input = new Scanner(System.in);
		ClienteController ClienteControl = new ClienteController();

		String hora;
		Calendar rightNow = Calendar.getInstance();
		int hora_atual = rightNow.get(Calendar.HOUR_OF_DAY);
		if (hora_atual >= 7 && hora_atual < 12)
			hora = "Bom dia!";
		else if (hora_atual >= 12 && hora_atual < 18)
			hora = "Boa tarde!";
		else
			hora = "Boa noite!";

		util.Logo();
		String Menu = MessageFormat.format("{0} Seja muito bem vindo ao Banco Next" + "\nescolha uma das opções abaixo"
				+ "\n\n• 1 - Já possui uma conta? Entre!" + "\n• 2 - Não possui uma conta? Crie uma totalmente grátis!"
				+ "\n• 0 - SAIR", hora);
		System.out.print(Menu);
		util.Linhas();
		System.out.print("Sua resposta >>> ");

		String Menu_Login = input.nextLine();
		switch (Menu_Login) {
		case "1":
			util.Logo();
			System.out.print("Escolha uma das opções abaixo" + "\n\n1 - Entrar com CPF" + "\n2 - Entrar com Email"
					+ "\n3 - Voltar" + "\n0 - SAIR");
			util.Linhas();
			System.out.print("Sua resposta >>> ");
			String Menu_Entrar = input.nextLine();
			while (Menu_Entrar != "0") {
				switch (Menu_Entrar) {
				case "1":

					System.out.print("\n• Informe seu CPF: ");
					String cpf = input.next();
					ClienteControl.ValidarCPF(cpf);

					System.out.print("• Informe sua senha: ");
					String senha = input.next();
					ClienteControl.ValidarSenha(senha);

					ClienteControl.buscarClienteCPF(cpf, senha);
					break;
				case "2":

					// Entrar na conta atraves do Email

					System.out.print("\n• Informe seu Email: ");
					String email = input.nextLine().toUpperCase();
					ClienteControl.ValidarEmail(email);

					System.out.print("• Informe sua senha: ");
					String SenhaEmail = input.nextLine();
					ClienteControl.ValidarSenha(SenhaEmail);

					ClienteControl.buscarClienteEmail(email, SenhaEmail);
					break;
				case "3":
					Next();
					break;
				case "0":
					System.out.print("Muito obrigado por utilizar o Banco Next");
					System.exit(0);
					break;
				default:
					Next();
				}
				break;
			}
			break;

		case "2":

			util.Logo();
			Ligacao ligue = new Ligacao();
			
			
			System.out.print("• Informe seu nome completo: ");
			String nome = input.nextLine().toUpperCase();
			ClienteControl.validarNome(nome);

			Scanner input1 = new Scanner(System.in);
			System.out.print("• Informe seu CPF sem pontos e traços: ");
			String cpf = input1.next();
			ClienteControl.ValidarCPF(cpf);

			System.out.print("• Informe seu email: ");
			String email = input1.next().toUpperCase();
			ClienteControl.ValidarEmail(email);

			System.out.print("• Informe uma senha de 4 digitos: ");
			String senha = input1.next();
			ClienteControl.ValidarSenha(senha);

			ligue.Ligar(nome, cpf, email, senha);
			Next();

			break;
		case "0":
			System.out.print("Obrigado por utilizar o Banco Next!");
			System.exit(0);
		default:
			System.out.print("Digite alguma das opções acima!");
			Next();
		}
		input.close();
	}

	public static void MenuConta() {

		Utils.Utilidades ferramentas = new Utils.Utilidades();
		ContaController ContaControl = new ContaController();

		String TipoCliente;
		if (SaldoUsuario >= 15000) {
			TipoCliente = "Premium";
		} else if (SaldoUsuario >= 5000 && SaldoUsuario <= 14999) {
			TipoCliente = "Super";
		} else {
			TipoCliente = "Comum";
		}

		Scanner input = new Scanner(System.in);

		ferramentas.Logo();
		System.out.print("\nOlá " + NomeUsuario + "\nSaldo: " + SaldoUsuario);
		ferramentas.Linhas();
		System.out.print("\nEscolha uma das opções abaixo\n" + "\n1 - Depositar" + "\n2 - Transferir" + "\n3 - Sacar"
				+ "\n4 - Dados Pessoais" + "\n5 - Voltar para a tela de Login" + "\n0 - SAIR");
		ferramentas.Linhas();

		System.out.print("Sua escolha >>> ");
		String escolha_menu = input.next();

		while (escolha_menu != "0") {
			switch (escolha_menu) {
			case "1":
				ferramentas.Logo();

				System.out.print("• Informe o valor a ser depositado: ");
				try {
					double DepositarValor = input.nextDouble();
					while (DepositarValor <= 0) {
						System.out.print("Valor Invalido! Informe o valor a ser depositado: ");
						DepositarValor = input.nextDouble();
					}
					ContaControl.DepositarDinheiro(NumeroUsuario, DepositarValor);
					MenuConta();
				} catch (java.util.InputMismatchException e) {
					System.out.print("Informe apenas Nuemros!");
					MenuConta();
				}

				break;
			case "2":
				ferramentas.Logo();

				System.out.print("• Informe o numero da conta do destinatario: ");
				String NumeroDestinatario = input.next();
				ContaControl.VerificarNumeroConta(NumeroDestinatario);

				System.out.print("• Informe o saldo na qual você queira transferir: ");
				try {
					double ValorTransferencia = input.nextDouble();
					while (ValorTransferencia <= 0) {
						System.out.print("Valor Invalido! Informe o valor a ser depositado: ");
						ValorTransferencia = input.nextDouble();
					}
					ContaControl.TransferirDinheiro(NumeroUsuario, NumeroDestinatario, ValorTransferencia);
					MenuConta();
				} catch (java.util.InputMismatchException e) {
					System.out.print("Informe apenas Nuemros!");
					MenuConta();
				}
				break;
			case "3":
				ferramentas.Logo();

				System.out.print("• Informe o valor do saque: ");
				try {
					double SaqueValor = input.nextDouble();
					while (SaqueValor <= 0) {
						System.out.print("Valor Invalido! Informe o valor a ser depositado: ");
						SaqueValor = input.nextDouble();
					}

					ContaControl.ValorSaqueDinheiro(NumeroUsuario, SaqueValor);
					MenuConta();
				} catch (java.util.InputMismatchException e) {
					System.out.print("Informe apenas Nuemros!");
					MenuConta();
				}

				break;
			case "4":
				ferramentas.Logo();
				input = new Scanner(System.in);
				String DadosPessoais = MessageFormat.format(
						"                  DADOS PESSOAIS" + "\n\n• Nome: {0}" + "\n• CPF: {1}" + "\n• Email: {2}",
						NomeUsuario, CpfUsuario, EmailUsuario);
				System.out.print(DadosPessoais);
				ferramentas.Linhas();
				String DadosCartao = MessageFormat.format("              INFORMACOES SOBRE SUA CONTA"
						+ "\n\n• Numero da Conta: {0}" + "\n• Saldo: {1}" + "\n• Cliente: {2}", NumeroUsuario,
						SaldoUsuario, TipoCliente);
				System.out.print(DadosCartao);
				ferramentas.Linhas();
				System.out.print("\nEscolha uma das opções abaixo" + "\n\n1 - Voltar pro Menu de Consultas"
						+ "\n2 - Voltar pro Menu de Login" + "\n0 - SAIR");
				ferramentas.Linhas();

				System.out.print("Sua escolha: ");
				String opcoesDados = input.nextLine();
				while (opcoesDados != "0") {
					switch (opcoesDados) {
					case "1":
						MenuConta();
						break;
					case "2":
						Next();
						break;
					case "0":
						System.out.print("Muito obrigado por utilizar o Banco Next");
						System.exit(0);
					default:
						MenuConta();
						break;
					}
					break;
				}
				break;
			case "5":
				System.out.print("Voltando para a tela de login!");
				Next();
				break;
			case "0":
				System.out.print("Muito obrigado por utilizar o Banco Next");
				System.exit(0);
			default:
				MenuConta();
				break;
			}
			input.close();
			break;
		}
	}
}