package br.com.projetonext.controller;

import java.util.Random;
import java.util.Scanner;

import br.com.projetonext.model.Conta;
import br.com.projetonext.view.Application;

public class ContaController {
	private String NumeroConta = "";
	private double SaldoConta = 0.00;

	public String getNumeroConta() {
		return NumeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		NumeroConta = numeroConta;
	}

	public double getSaldoConta() {
		return SaldoConta;
	}

	public void setSaldoConta(double saldoConta) {
		SaldoConta = saldoConta;
	}

	public String CriarContaControl() {
		Conta conta = new Conta();

		int min = 100000, max = 999999;
		Random random = new Random();
		int num1 = 0;
		for (int i = 0; i <= 5; i++) {
			num1 = random.nextInt(max + min) + min;
			NumeroConta = "'" + num1 + "'";
		}

		conta.CriarConta(NumeroConta, SaldoConta);
		return "Account created";

	}

	public void BuscarDadosCPF(String cpf) {
		Conta conta = new Conta();
		conta.BuscarContaCpf(cpf);

		setSaldoConta(conta.getSaldoConta());
		Application.SaldoUsuario = getSaldoConta();

		setNumeroConta(conta.getNumeroConta());
		Application.NumeroUsuario = getNumeroConta();

	}

	public void BuscarDadosEmail(String email) {
		Conta conta = new Conta();
		conta.BuscarContaEmail(email);

		setSaldoConta(conta.getSaldoConta());
		Application.SaldoUsuario = getSaldoConta();

		setNumeroConta(conta.getNumeroConta());
		Application.NumeroUsuario = getNumeroConta();
	}

	public void VerificarNumeroConta(String NumeroConta) {

		try {
			Long.parseLong(NumeroConta);
			while (NumeroConta.length() != 6) {
				Scanner input = new Scanner(System.in);
				System.out.print("Numero invalido! Informe o numero da conta correto: ");
				NumeroConta = input.nextLine();
				input.close();
			}
		} catch (Exception e) {
			System.out.print("O numero da conta só pode conter numeros!");
			Application.MenuConta();
		}

	}

	public void DepositarDinheiro(String NumeroConta, double DepositarValor) {
		Conta conta = new Conta();
		conta.Depositar(NumeroConta, DepositarValor);
		setSaldoConta(conta.getSaldoConta());
	}

	public void ValorSaqueDinheiro(String NumeroConta, double ValorSaque) {
		Conta conta = new Conta();
		conta.SacarDinheiro(NumeroConta, ValorSaque);
		setSaldoConta(conta.getSaldoConta());
	}

	public void TransferirDinheiro(String NumeroRemetente, String NumeroDestinatario, double ValorTransferencia) {
		Conta conta = new Conta();
		conta.Transferencia(NumeroRemetente, NumeroDestinatario, ValorTransferencia);
		setSaldoConta(conta.getSaldoConta());
	}

	public void VoltaMenuConta() {
		Application.MenuConta();
	}

}
